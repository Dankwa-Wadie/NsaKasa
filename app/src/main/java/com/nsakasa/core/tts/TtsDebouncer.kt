package com.nsakasa.core.tts

import com.nsakasa.core.ml.GestureResult

/**
 * Debouncer logic ensuring gesture speech is not spammed every frame.
 * Only triggers speech if confidence >= threshold (0.75) AND either label changes OR 2s elapsed.
 */
class TtsDebouncer(
    private val ttsManager: TtsManager,
    var confidenceThreshold: Float = 0.75f,
    var debounceCooldownMs: Long = 2000L
) {
    private var lastSpokenLabel: String = ""
    private var lastSpokenTimeMs: Long = 0L

    fun processResult(result: GestureResult) {
        if (result.confidence < confidenceThreshold) return
        if (result.label == "No Hand Detected" || result.label == "Waiting for Gesture...") return

        val currentTimeMs = System.currentTimeMillis()
        val isNewLabel = result.label != lastSpokenLabel
        val timeElapsedMs = currentTimeMs - lastSpokenTimeMs

        if (isNewLabel || timeElapsedMs >= debounceCooldownMs) {
            ttsManager.speak(result.label)
            lastSpokenLabel = result.label
            lastSpokenTimeMs = currentTimeMs
        }
    }
}
