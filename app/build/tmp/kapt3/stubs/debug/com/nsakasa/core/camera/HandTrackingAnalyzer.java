package com.nsakasa.core.camera;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\u0006\u0010\u0014\u001a\u00020\u0011J \u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001d"}, d2 = {"Lcom/nsakasa/core/camera/HandTrackingAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "context", "Landroid/content/Context;", "modelPath", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_resultFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "handLandmarker", "Lcom/google/mediapipe/tasks/vision/handlandmarker/HandLandmarker;", "resultFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "getResultFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "close", "handleResult", "result", "Lcom/google/mediapipe/tasks/vision/handlandmarker/HandLandmarkerResult;", "width", "", "height", "setupHandLandmarker", "Companion", "app_debug"})
public final class HandTrackingAnalyzer implements androidx.camera.core.ImageAnalysis.Analyzer {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String modelPath = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.nsakasa.core.camera.HandLandmarkResult> _resultFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.nsakasa.core.camera.HandLandmarkResult> resultFlow = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker handLandmarker;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "HandTrackingAnalyzer";
    @org.jetbrains.annotations.NotNull()
    public static final com.nsakasa.core.camera.HandTrackingAnalyzer.Companion Companion = null;
    
    public HandTrackingAnalyzer(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String modelPath) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.nsakasa.core.camera.HandLandmarkResult> getResultFlow() {
        return null;
    }
    
    private final void setupHandLandmarker() {
    }
    
    @java.lang.Override()
    @androidx.camera.core.ExperimentalGetImage()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
    
    private final void handleResult(com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult result, int width, int height) {
    }
    
    public final void close() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/nsakasa/core/camera/HandTrackingAnalyzer$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}