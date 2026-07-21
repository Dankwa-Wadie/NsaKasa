package com.nsakasa.features.cameratranslate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0007J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/nsakasa/features/cameratranslate/CameraTranslateViewModel;", "Landroidx/lifecycle/ViewModel;", "gestureClassifier", "Lcom/nsakasa/core/ml/GestureClassifierInterface;", "(Lcom/nsakasa/core/ml/GestureClassifierInterface;)V", "_isPermissionGranted", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_landmarkResult", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "gestureResult", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/nsakasa/core/ml/GestureResult;", "getGestureResult", "()Lkotlinx/coroutines/flow/StateFlow;", "isPermissionGranted", "landmarkResult", "getLandmarkResult", "onCleared", "", "setPermissionGranted", "granted", "updateLandmarkResult", "result", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CameraTranslateViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier = null;
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
    
    @javax.inject.Inject()
    public CameraTranslateViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier) {
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