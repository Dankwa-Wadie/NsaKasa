package com.nsakasa.features.cameratranslate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u000e\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u000bJ\u000e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\rR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/nsakasa/features/cameratranslate/CameraTranslateViewModel;", "Landroidx/lifecycle/ViewModel;", "gestureClassifier", "Lcom/nsakasa/core/ml/GestureClassifierInterface;", "ttsDebouncer", "Lcom/nsakasa/core/tts/TtsDebouncer;", "conversationDao", "Lcom/nsakasa/core/data/ConversationDao;", "(Lcom/nsakasa/core/ml/GestureClassifierInterface;Lcom/nsakasa/core/tts/TtsDebouncer;Lcom/nsakasa/core/data/ConversationDao;)V", "_isPermissionGranted", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_landmarkResult", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "gestureResult", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/nsakasa/core/ml/GestureResult;", "getGestureResult", "()Lkotlinx/coroutines/flow/StateFlow;", "isPermissionGranted", "landmarkResult", "getLandmarkResult", "lastLoggedLabel", "", "lastLoggedTime", "", "onCleared", "", "setPermissionGranted", "granted", "updateLandmarkResult", "result", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CameraTranslateViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.tts.TtsDebouncer ttsDebouncer = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.data.ConversationDao conversationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.camera.HandLandmarkResult> _landmarkResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.camera.HandLandmarkResult> landmarkResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> gestureResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isPermissionGranted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPermissionGranted = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastLoggedLabel = "";
    private long lastLoggedTime = 0L;
    
    @javax.inject.Inject()
    public CameraTranslateViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.tts.TtsDebouncer ttsDebouncer, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.data.ConversationDao conversationDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.camera.HandLandmarkResult> getLandmarkResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> getGestureResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isPermissionGranted() {
        return null;
    }
    
    public final void updateLandmarkResult(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.camera.HandLandmarkResult result) {
    }
    
    public final void setPermissionGranted(boolean granted) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}