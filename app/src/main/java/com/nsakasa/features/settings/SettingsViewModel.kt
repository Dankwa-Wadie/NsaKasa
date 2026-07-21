package com.nsakasa.features.settings

import androidx.lifecycle.ViewModel
import com.nsakasa.core.tts.TtsDebouncer
import com.nsakasa.core.tts.TtsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val ttsManager: TtsManager,
    private val ttsDebouncer: TtsDebouncer
) : ViewModel() {

    private val _confidenceThreshold = MutableStateFlow(ttsDebouncer.confidenceThreshold)
    val confidenceThreshold: StateFlow<Float> = _confidenceThreshold.asStateFlow()

    private val _speechRate = MutableStateFlow(ttsManager.speechRate)
    val speechRate: StateFlow<Float> = _speechRate.asStateFlow()

    fun updateConfidenceThreshold(value: Float) {
        _confidenceThreshold.value = value
        ttsDebouncer.confidenceThreshold = value
    }

    fun updateSpeechRate(value: Float) {
        _speechRate.value = value
        ttsManager.speechRate = value
    }
}
