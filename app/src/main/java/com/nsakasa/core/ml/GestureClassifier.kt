package com.nsakasa.core.ml

import android.content.Context
import android.util.Log
import com.nsakasa.core.camera.HandLandmarkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

/**
 * TensorFlow Lite gesture classifier running inference over a rolling buffer of 30 landmark frames.
 * Expected input shape: [1, 30, 21, 3] (30 frames, 21 landmarks, x/y/z coordinates).
 */
class GestureClassifier(
    private val context: Context,
    private val modelPath: String = "models/gsl_gesture_classifier.tflite"
) : GestureClassifierInterface {

    private val _gestureResultFlow = MutableStateFlow(
        GestureResult("Initializing ML...", 0.0f)
    )
    override val gestureResultFlow: StateFlow<GestureResult> = _gestureResultFlow.asStateFlow()

    private var interpreter: Interpreter? = null
    private var fakeFallback: FakeGestureClassifier? = null
    private var isUsingFallback = false

    // Rolling buffer holding 30 frames of [21, 3] float points
    private val sequenceLength = 30
    private val numLandmarks = 21
    private val numCoords = 3 // x, y, z
    private val frameBufferSize = numLandmarks * numCoords // 63 floats per frame
    
    // Deque buffer of float arrays for rolling window
    private val landmarkRingBuffer = ArrayDeque<FloatArray>()

    private val defaultLabels = listOf(
        "Akwaaba (Welcome)",
        "Thank You",
        "Hello",
        "Help",
        "Yes",
        "No",
        "Please",
        "Good Morning",
        "Stop"
    )

    init {
        setupInterpreter()
    }

    private fun setupInterpreter() {
        try {
            val modelBuffer = loadModelFile(context, modelPath)
            if (modelBuffer != null) {
                val options = Interpreter.Options().apply {
                    setNumThreads(4)
                }
                interpreter = Interpreter(modelBuffer, options)
                Log.d(TAG, "TFLite GestureClassifier successfully initialized with model: $modelPath")
            } else {
                Log.w(TAG, "Model file not found at assets/$modelPath. Using FakeGestureClassifier fallback.")
                enableFallback()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize TFLite interpreter: ${e.message}. Using FakeGestureClassifier fallback.", e)
            enableFallback()
        }
    }

    private fun enableFallback() {
        isUsingFallback = true
        fakeFallback = FakeGestureClassifier()
    }

    override fun processLandmarks(result: HandLandmarkResult) {
        if (isUsingFallback) {
            fakeFallback?.processLandmarks(result)
            fakeFallback?.gestureResultFlow?.value?.let { _gestureResultFlow.value = it }
            return
        }

        // Extract frame feature vector [63 floats]
        val currentFrameFeatures = FloatArray(frameBufferSize) { 0.0f }

        if (result.hands.isNotEmpty()) {
            val primaryHand = result.hands[0].points
            for (i in 0 until minOf(numLandmarks, primaryHand.size)) {
                val pt = primaryHand[i]
                currentFrameFeatures[i * 3] = pt.x
                currentFrameFeatures[i * 3 + 1] = pt.y
                currentFrameFeatures[i * 3 + 2] = pt.z
            }
        }

        // Add to rolling window
        landmarkRingBuffer.addLast(currentFrameFeatures)
        if (landmarkRingBuffer.size > sequenceLength) {
            landmarkRingBuffer.removeFirst()
        }

        // Only run inference when rolling buffer is full (30 frames)
        if (landmarkRingBuffer.size == sequenceLength) {
            runInference()
        }
    }

    private fun runInference() {
        val tflite = interpreter ?: return

        try {
            // Build ByteBuffer [1, 30, 21, 3] = 1 * 30 * 63 * 4 bytes
            val inputBuffer = ByteBuffer.allocateDirect(1 * sequenceLength * frameBufferSize * 4).apply {
                order(ByteOrder.nativeOrder())
            }

            for (frame in landmarkRingBuffer) {
                for (valFloat in frame) {
                    inputBuffer.putFloat(valFloat)
                }
            }

            val numClasses = defaultLabels.size
            val outputArray = Array(1) { FloatArray(numClasses) }

            tflite.run(inputBuffer, outputArray)

            val probabilities = outputArray[0]
            var maxIndex = 0
            var maxProb = 0.0f

            for (i in probabilities.indices) {
                if (probabilities[i] > maxProb) {
                    maxProb = probabilities[i]
                    maxIndex = i
                }
            }

            val predictedLabel = if (maxIndex in defaultLabels.indices) defaultLabels[maxIndex] else "Unknown"
            _gestureResultFlow.value = GestureResult(
                label = predictedLabel,
                confidence = maxProb,
                timestampMs = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error running TFLite inference: ${e.message}", e)
        }
    }

    private fun loadModelFile(context: Context, path: String): ByteBuffer? {
        return try {
            val fileDescriptor = context.assets.openFd(path)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        } catch (e: Exception) {
            null
        }
    }

    override fun close() {
        interpreter?.close()
        interpreter = null
        fakeFallback?.close()
    }

    companion object {
        private const val TAG = "GestureClassifier"
    }
}
