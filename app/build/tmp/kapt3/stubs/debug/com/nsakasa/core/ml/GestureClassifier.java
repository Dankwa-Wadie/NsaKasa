package com.nsakasa.core.ml;

/**
 * TensorFlow Lite gesture classifier running inference over a rolling buffer of 30 landmark frames.
 * Expected input shape: [1, 30, 21, 3] (30 frames, 21 landmarks, x/y/z coordinates).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001fH\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000fX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/nsakasa/core/ml/GestureClassifier;", "Lcom/nsakasa/core/ml/GestureClassifierInterface;", "context", "Landroid/content/Context;", "modelPath", "", "(Landroid/content/Context;Ljava/lang/String;)V", "_gestureResultFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/nsakasa/core/ml/GestureResult;", "defaultLabels", "", "fakeFallback", "Lcom/nsakasa/core/ml/FakeGestureClassifier;", "frameBufferSize", "", "gestureResultFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getGestureResultFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "interpreter", "Lorg/tensorflow/lite/Interpreter;", "isUsingFallback", "", "landmarkRingBuffer", "Lkotlin/collections/ArrayDeque;", "", "numCoords", "numLandmarks", "sequenceLength", "close", "", "enableFallback", "loadModelFile", "Ljava/nio/ByteBuffer;", "path", "processLandmarks", "result", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "runInference", "setupInterpreter", "Companion", "app_debug"})
public final class GestureClassifier implements com.nsakasa.core.ml.GestureClassifierInterface {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String modelPath = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.ml.GestureResult> _gestureResultFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> gestureResultFlow = null;
    @org.jetbrains.annotations.Nullable()
    private org.tensorflow.lite.Interpreter interpreter;
    @org.jetbrains.annotations.Nullable()
    private com.nsakasa.core.ml.FakeGestureClassifier fakeFallback;
    private boolean isUsingFallback = false;
    private final int sequenceLength = 30;
    private final int numLandmarks = 21;
    private final int numCoords = 3;
    private final int frameBufferSize = 0;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.collections.ArrayDeque<float[]> landmarkRingBuffer = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> defaultLabels = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "GestureClassifier";
    @org.jetbrains.annotations.NotNull()
    public static final com.nsakasa.core.ml.GestureClassifier.Companion Companion = null;
    
    public GestureClassifier(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String modelPath) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> getGestureResultFlow() {
        return null;
    }
    
    private final void setupInterpreter() {
    }
    
    private final void enableFallback() {
    }
    
    @java.lang.Override()
    public void processLandmarks(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.camera.HandLandmarkResult result) {
    }
    
    private final void runInference() {
    }
    
    private final java.nio.ByteBuffer loadModelFile(android.content.Context context, java.lang.String path) {
        return null;
    }
    
    @java.lang.Override()
    public void close() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/nsakasa/core/ml/GestureClassifier$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}