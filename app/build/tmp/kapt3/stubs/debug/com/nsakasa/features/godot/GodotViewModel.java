package com.nsakasa.features.godot;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lcom/nsakasa/features/godot/GodotViewModel;", "Landroidx/lifecycle/ViewModel;", "godotBridgePlugin", "Lcom/nsakasa/core/godot/GodotBridgePlugin;", "(Lcom/nsakasa/core/godot/GodotBridgePlugin;)V", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/nsakasa/core/godot/GodotState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onEngineStarted", "", "onGestureSelected", "gesture", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GodotViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.godot.GodotBridgePlugin godotBridgePlugin = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.godot.GodotState> uiState = null;
    
    @javax.inject.Inject()
    public GodotViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.godot.GodotBridgePlugin godotBridgePlugin) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.godot.GodotState> getUiState() {
        return null;
    }
    
    public final void onGestureSelected(@org.jetbrains.annotations.NotNull()
    java.lang.String gesture) {
    }
    
    public final void onEngineStarted() {
    }
}