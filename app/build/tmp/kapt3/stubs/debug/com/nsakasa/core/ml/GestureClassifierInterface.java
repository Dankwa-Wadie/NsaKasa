package com.nsakasa.core.ml;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/nsakasa/core/ml/GestureClassifierInterface;", "", "gestureResultFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/nsakasa/core/ml/GestureResult;", "getGestureResultFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "close", "", "processLandmarks", "result", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "app_debug"})
public abstract interface GestureClassifierInterface {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> getGestureResultFlow();
    
    public abstract void processLandmarks(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.camera.HandLandmarkResult result);
    
    public abstract void close();
}