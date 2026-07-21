package com.nsakasa.core.camera

/**
 * Represents a single 3D landmark coordinate normalized between 0.0 and 1.0.
 */
data class LandmarkPoint(
    val x: Float,
    val y: Float,
    val z: Float
)

/**
 * Collection of 21 landmark points for a detected hand.
 */
data class HandLandmarks(
    val points: List<LandmarkPoint>
)

/**
 * Container holding detected hands result from an image frame.
 */
data class HandLandmarkResult(
    val hands: List<HandLandmarks>,
    val inputWidth: Int,
    val inputHeight: Int,
    val timestampMs: Long
)
