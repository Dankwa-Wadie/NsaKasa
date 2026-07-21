package com.nsakasa.features.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u000bJ\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u000bR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/nsakasa/features/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "ttsManager", "Lcom/nsakasa/core/tts/TtsManager;", "ttsDebouncer", "Lcom/nsakasa/core/tts/TtsDebouncer;", "otaUpdateManager", "Lcom/nsakasa/core/ota/OtaUpdateManager;", "(Lcom/nsakasa/core/tts/TtsManager;Lcom/nsakasa/core/tts/TtsDebouncer;Lcom/nsakasa/core/ota/OtaUpdateManager;)V", "_confidenceThreshold", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_speechRate", "confidenceThreshold", "Lkotlinx/coroutines/flow/StateFlow;", "getConfidenceThreshold", "()Lkotlinx/coroutines/flow/StateFlow;", "otaState", "Lcom/nsakasa/core/ota/OtaUpdateState;", "getOtaState", "speechRate", "getSpeechRate", "checkForAppUpdates", "", "downloadUpdateApk", "url", "", "installUpdate", "context", "Landroid/content/Context;", "apkFile", "Ljava/io/File;", "updateConfidenceThreshold", "value", "updateSpeechRate", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.tts.TtsManager ttsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.tts.TtsDebouncer ttsDebouncer = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.ota.OtaUpdateManager otaUpdateManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ota.OtaUpdateState> otaState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Float> _confidenceThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Float> confidenceThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Float> _speechRate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Float> speechRate = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.tts.TtsManager ttsManager, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.tts.TtsDebouncer ttsDebouncer, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.ota.OtaUpdateManager otaUpdateManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ota.OtaUpdateState> getOtaState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Float> getConfidenceThreshold() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Float> getSpeechRate() {
        return null;
    }
    
    public final void updateConfidenceThreshold(float value) {
    }
    
    public final void updateSpeechRate(float value) {
    }
    
    public final void checkForAppUpdates() {
    }
    
    public final void downloadUpdateApk(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void installUpdate(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File apkFile) {
    }
}