package com.nsakasa.features.cameratranslate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.camera.HandLandmarkResult
import com.nsakasa.core.data.ConversationDao
import com.nsakasa.core.data.ConversationEntity
import com.nsakasa.core.ml.GestureClassifierInterface
import com.nsakasa.core.ml.GestureResult
import com.nsakasa.core.tts.TtsDebouncer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraTranslateViewModel @Inject constructor(
    private val gestureClassifier: GestureClassifierInterface,
    private val ttsDebouncer: TtsDebouncer,
    private val conversationDao: ConversationDao
) : ViewModel() {

    private val _landmarkResult = MutableStateFlow<HandLandmarkResult?>(null)
    val landmarkResult: StateFlow<HandLandmarkResult?> = _landmarkResult.asStateFlow()

    val gestureResult: StateFlow<GestureResult> = gestureClassifier.gestureResultFlow

    private val _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted: StateFlow<Boolean> = _isPermissionGranted.asStateFlow()

    private var lastLoggedLabel = ""
    private var lastLoggedTime = 0L

    init {
        viewModelScope.launch {
            gestureResult.collect { result ->
                if (result.confidence >= 0.75f &&
                    result.label != "No Hand Detected" &&
                    result.label != "Waiting for Gesture..."
                ) {
                    // Trigger TTS via debouncer
                    ttsDebouncer.processResult(result)

                    // Log entry into Room database with 3s deduplication
                    val currentTime = System.currentTimeMillis()
                    if (result.label != lastLoggedLabel || (currentTime - lastLoggedTime) > 3000L) {
                        conversationDao.insert(
                            ConversationEntity(
                                type = "GESTURE_TO_SPEECH",
                                text = result.label,
                                confidence = result.confidence,
                                timestamp = currentTime
                            )
                        )
                        lastLoggedLabel = result.label
                        lastLoggedTime = currentTime
                    }
                }
            }
        }
    }

    fun updateLandmarkResult(result: HandLandmarkResult) {
        viewModelScope.launch {
            _landmarkResult.emit(result)
            gestureClassifier.processLandmarks(result)
        }
    }

    fun setPermissionGranted(granted: Boolean) {
        _isPermissionGranted.value = granted
    }

    override fun onCleared() {
        super.onCleared()
        gestureClassifier.close()
    }
}
