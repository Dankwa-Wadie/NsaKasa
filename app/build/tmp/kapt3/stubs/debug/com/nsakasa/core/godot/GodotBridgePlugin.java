package com.nsakasa.core.godot;

/**
 * Bridge singleton for communication between MediaPipe Gesture Recognition
 * in Android (Kotlin) and the Godot 3D Avatar scene.
 */
@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lcom/nsakasa/core/godot/GodotBridgePlugin;", "", "()V", "_godotState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/nsakasa/core/godot/GodotState;", "godotState", "Lkotlinx/coroutines/flow/StateFlow;", "getGodotState", "()Lkotlinx/coroutines/flow/StateFlow;", "nativeSendGesture", "", "gestureName", "", "onGodotEngineReady", "sendGestureToAvatar", "app_debug"})
public final class GodotBridgePlugin {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.godot.GodotState> _godotState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.godot.GodotState> godotState = null;
    
    @javax.inject.Inject()
    public GodotBridgePlugin() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.godot.GodotState> getGodotState() {
        return null;
    }
    
    public final void onGodotEngineReady() {
    }
    
    public final void sendGestureToAvatar(@org.jetbrains.annotations.NotNull()
    java.lang.String gestureName) {
    }
    
    private final void nativeSendGesture(java.lang.String gestureName) {
    }
}