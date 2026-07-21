package com.nsakasa.core.ml

import com.nsakasa.core.camera.HandLandmarkResult
import kotlinx.coroutines.flow.StateFlow

interface GestureClassifierInterface {
    val gestureResultFlow: StateFlow<GestureResult>
    fun processLandmarks(result: HandLandmarkResult)
    fun close()
}
