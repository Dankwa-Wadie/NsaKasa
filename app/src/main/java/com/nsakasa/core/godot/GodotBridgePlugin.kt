package com.nsakasa.core.godot

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * State representing the status of the embedded Godot 3D Engine runtime.
 */
data class GodotState(
    val isInitialized: Boolean = false,
    val currentGesture: String = "IDLE",
    val statusMessage: String = "Godot 3D Avatar Ready"
)

/**
 * Bridge singleton for communication between MediaPipe Gesture Recognition
 * in Android (Kotlin) and the Godot 3D Avatar scene.
 */
@Singleton
class GodotBridgePlugin @Inject constructor() {

    private val _godotState = MutableStateFlow(GodotState())
    val godotState: StateFlow<GodotState> = _godotState.asStateFlow()

    fun onGodotEngineReady() {
        _godotState.value = _godotState.value.copy(
            isInitialized = true,
            statusMessage = "Godot Engine Running"
        )
    }

    fun sendGestureToAvatar(gestureName: String) {
        _godotState.value = _godotState.value.copy(
            currentGesture = gestureName,
            statusMessage = "Avatar Rendering: $gestureName"
        )
        // Signal emitted to Godot GDScript via JNI when GodotHost is bound
        nativeSendGesture(gestureName)
    }

    private fun nativeSendGesture(gestureName: String) {
        // Hooks into Godot JNI instance when Godot Fragment/Host is active
    }
}
