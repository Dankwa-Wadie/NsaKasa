package com.nsakasa.features.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.ota.OtaUpdateManager
import com.nsakasa.core.ota.OtaUpdateState
import com.nsakasa.core.tts.TtsDebouncer
import com.nsakasa.core.tts.TtsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val ttsManager: TtsManager,
    private val ttsDebouncer: TtsDebouncer,
    private val otaUpdateManager: OtaUpdateManager
) : ViewModel() {

    val otaState: StateFlow<OtaUpdateState> = otaUpdateManager.updateState

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

    fun checkForAppUpdates() {
        viewModelScope.launch {
            otaUpdateManager.checkForUpdates()
        }
    }

    fun downloadUpdateApk(url: String) {
        viewModelScope.launch {
            otaUpdateManager.downloadApk(url)
        }
    }

    fun installUpdate(context: Context, apkFile: File) {
        otaUpdateManager.triggerApkInstallation(context, apkFile)
    }
}
