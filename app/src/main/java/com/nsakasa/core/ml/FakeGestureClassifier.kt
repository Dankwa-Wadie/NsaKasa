package com.nsakasa.core.ml

import com.nsakasa.core.camera.HandLandmarkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

/**
 * Mock gesture classifier cycling through a test GSL gesture list.
 * Enables full UI building & testing before the real TFLite model is trained.
 */
class FakeGestureClassifier : GestureClassifierInterface {

    private val sampleLabels = listOf(
        "Akwaaba (Welcome)",
        "Thank You",
        "Hello",
        "Help",
        "Yes",
        "No",
        "Please",
        "Good Morning",
        "I Love You"
    )

    private val _gestureResultFlow = MutableStateFlow(
        GestureResult(
            label = "Waiting for Gesture...",
            confidence = 0.0f,
            isFake = true
        )
    )
    override val gestureResultFlow: StateFlow<GestureResult> = _gestureResultFlow.asStateFlow()

    private var frameCounter = 0
    private var currentIndex = 0

    override fun processLandmarks(result: HandLandmarkResult) {
        if (result.hands.isEmpty()) {
            _gestureResultFlow.value = GestureResult(
                label = "No Hand Detected",
                confidence = 0.0f,
                isFake = true
            )
            return
        }

        frameCounter++
        // Simulate continuous classification every 15 frames (~0.5s at 30fps)
        if (frameCounter % 15 == 0) {
            val label = sampleLabels[currentIndex % sampleLabels.size]
            val confidence = 0.82f + Random.nextFloat() * 0.16f // 0.82 - 0.98
            currentIndex++

            _gestureResultFlow.value = GestureResult(
                label = label,
                confidence = confidence,
                timestampMs = System.currentTimeMillis(),
                isFake = true
            )
        }
    }

    override fun close() {
        // No resources to release
    }
}
