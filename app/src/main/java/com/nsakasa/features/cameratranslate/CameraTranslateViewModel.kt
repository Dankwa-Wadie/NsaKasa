package com.nsakasa.features.cameratranslate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.camera.HandLandmarkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraTranslateViewModel @Inject constructor() : ViewModel() {

    private val _landmarkResult = MutableStateFlow<HandLandmarkResult?>(null)
    val landmarkResult: StateFlow<HandLandmarkResult?> = _landmarkResult.asStateFlow()

    private val _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted: StateFlow<Boolean> = _isPermissionGranted.asStateFlow()

    fun updateLandmarkResult(result: HandLandmarkResult) {
        viewModelScope.launch {
            _landmarkResult.emit(result)
        }
    }

    fun setPermissionGranted(granted: Boolean) {
        _isPermissionGranted.value = granted
    }
}
