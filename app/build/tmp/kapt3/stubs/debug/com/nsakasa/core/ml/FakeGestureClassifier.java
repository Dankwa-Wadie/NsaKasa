package com.nsakasa.core.ml;

/**
 * Mock gesture classifier cycling through a test GSL gesture list.
 * Enables full UI building & testing before the real TFLite model is trained.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/nsakasa/core/ml/FakeGestureClassifier;", "Lcom/nsakasa/core/ml/GestureClassifierInterface;", "()V", "_gestureResultFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/nsakasa/core/ml/GestureResult;", "currentIndex", "", "frameCounter", "gestureResultFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getGestureResultFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "sampleLabels", "", "", "close", "", "processLandmarks", "result", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "app_debug"})
public final class FakeGestureClassifier implements com.nsakasa.core.ml.GestureClassifierInterface {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> sampleLabels = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.ml.GestureResult> _gestureResultFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> gestureResultFlow = null;
    private int frameCounter = 0;
    private int currentIndex = 0;
    
    public FakeGestureClassifier() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> getGestureResultFlow() {
        return null;
    }
    
    @java.lang.Override()
    public void processLandmarks(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.camera.HandLandmarkResult result) {
    }
    
    @java.lang.Override()
    public void close() {
    }
}