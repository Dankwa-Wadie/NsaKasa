package com.nsakasa.core.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

/**
 * Manages Android TextToSpeech engine lifecycle and speech playback.
 */
class TtsManager(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var isInitialized = false

    var isSpeechEnabled: Boolean = true
        set(value) {
            field = value
            if (!value) {
                stop()
            }
        }

    var speechRate: Float = 1.0f
        set(value) {
            field = value
            tts?.setSpeechRate(value)
        }

    var pitch: Float = 1.0f
        set(value) {
            field = value
            tts?.setPitch(value)
        }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "English TTS language is missing or not supported")
            } else {
                isInitialized = true
                Log.d(TAG, "TextToSpeech successfully initialized")
            }
        } else {
            Log.e(TAG, "Failed to initialize TextToSpeech engine")
        }
    }

    fun speak(text: String) {
        if (!isSpeechEnabled) return
        if (!isInitialized) {
            Log.w(TAG, "TTS requested to speak before initialization finished")
            return
        }
        if (text.isBlank()) return

        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "nsa_kasa_tts_${System.currentTimeMillis()}")
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
        isInitialized = false
    }

    companion object {
        private const val TAG = "TtsManager"
    }
}
