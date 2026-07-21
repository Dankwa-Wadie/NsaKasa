package com.nsakasa.core.camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.SystemClock
import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class HandTrackingAnalyzer(
    private val context: Context,
    private val modelPath: String = "models/hand_landmarker.task"
) : ImageAnalysis.Analyzer {

    private val _resultFlow = MutableSharedFlow<HandLandmarkResult>(extraBufferCapacity = 1)
    val resultFlow: SharedFlow<HandLandmarkResult> = _resultFlow.asSharedFlow()

    private var handLandmarker: HandLandmarker? = null

    init {
        setupHandLandmarker()
    }

    private fun setupHandLandmarker() {
        try {
            val baseOptions = BaseOptions.builder()
                .setModelAssetPath(modelPath)
                .build()

            val options = HandLandmarker.HandLandmarkerOptions.builder()
                .setBaseOptions(baseOptions)
                .setMinHandDetectionConfidence(0.5f)
                .setMinTrackingConfidence(0.5f)
                .setMinHandPresenceConfidence(0.5f)
                .setNumHands(2)
                .setRunningMode(RunningMode.LIVE_STREAM)
                .setResultListener { result: HandLandmarkerResult, mpImage ->
                    handleResult(result, mpImage.width, mpImage.height)
                }
                .setErrorListener { error ->
                    Log.e(TAG, "MediaPipe HandLandmarker error: ${error.message}", error)
                }
                .build()

            handLandmarker = HandLandmarker.createFromOptions(context, options)
            Log.d(TAG, "HandLandmarker successfully initialized with asset path: $modelPath")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize HandLandmarker: ${e.message}", e)
        }
    }

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val landmarker = handLandmarker
        if (landmarker == null) {
            imageProxy.close()
            return
        }

        val frameTimeMs = SystemClock.uptimeMillis()

        try {
            val bitmap = imageProxy.toBitmap()
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            
            val rotatedBitmap = if (rotationDegrees != 0) {
                val matrix = Matrix().apply { postRotate(rotationDegrees.toFloat()) }
                Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                )
            } else {
                bitmap
            }

            val mpImage = BitmapImageBuilder(rotatedBitmap).build()
            landmarker.detectAsync(mpImage, frameTimeMs)
        } catch (e: Exception) {
            Log.e(TAG, "Error processing frame in HandTrackingAnalyzer: ${e.message}", e)
        } finally {
            imageProxy.close()
        }
    }

    private fun handleResult(result: HandLandmarkerResult, width: Int, height: Int) {
        val landmarksList = result.landmarks()
        val detectedHands = landmarksList.map { hand ->
            HandLandmarks(
                points = hand.map { landmark ->
                    LandmarkPoint(
                        x = landmark.x(),
                        y = landmark.y(),
                        z = landmark.z()
                    )
                }
            )
        }

        val landmarkResult = HandLandmarkResult(
            hands = detectedHands,
            inputWidth = width,
            inputHeight = height,
            timestampMs = System.currentTimeMillis()
        )
        _resultFlow.tryEmit(landmarkResult)
    }

    fun close() {
        handLandmarker?.close()
        handLandmarker = null
    }

    companion object {
        private const val TAG = "HandTrackingAnalyzer"
    }
}
