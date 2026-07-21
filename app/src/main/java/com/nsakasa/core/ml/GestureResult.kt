package com.nsakasa.core.ml

/**
 * Data class representing the result of gesture classification.
 */
data class GestureResult(
    val label: String,
    val confidence: Float,
    val timestampMs: Long = System.currentTimeMillis(),
    val isFake: Boolean = false
)
