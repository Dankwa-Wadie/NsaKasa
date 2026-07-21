package com.nsakasa.features.godot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.godot.GodotBridgePlugin
import com.nsakasa.core.godot.GodotState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class GodotViewModel @Inject constructor(
    private val godotBridgePlugin: GodotBridgePlugin
) : ViewModel() {

    val uiState: StateFlow<GodotState> = godotBridgePlugin.godotState

    fun onGestureSelected(gesture: String) {
        godotBridgePlugin.sendGestureToAvatar(gesture)
    }

    fun onEngineStarted() {
        godotBridgePlugin.onGodotEngineReady()
    }
}
