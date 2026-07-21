package com.nsakasa.core.tts;

/**
 * Debouncer logic ensuring gesture speech is not spammed every frame.
 * Only triggers speech if confidence >= threshold (0.75) AND either label changes OR 2s elapsed.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/nsakasa/core/tts/TtsDebouncer;", "", "ttsManager", "Lcom/nsakasa/core/tts/TtsManager;", "confidenceThreshold", "", "debounceCooldownMs", "", "(Lcom/nsakasa/core/tts/TtsManager;FJ)V", "getConfidenceThreshold", "()F", "setConfidenceThreshold", "(F)V", "getDebounceCooldownMs", "()J", "setDebounceCooldownMs", "(J)V", "lastSpokenLabel", "", "lastSpokenTimeMs", "processResult", "", "result", "Lcom/nsakasa/core/ml/GestureResult;", "app_debug"})
public final class TtsDebouncer {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.tts.TtsManager ttsManager = null;
    private float confidenceThreshold;
    private long debounceCooldownMs;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String lastSpokenLabel = "";
    private long lastSpokenTimeMs = 0L;
    
    public TtsDebouncer(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.tts.TtsManager ttsManager, float confidenceThreshold, long debounceCooldownMs) {
        super();
    }
    
    public final float getConfidenceThreshold() {
        return 0.0F;
    }
    
    public final void setConfidenceThreshold(float p0) {
    }
    
    public final long getDebounceCooldownMs() {
        return 0L;
    }
    
    public final void setDebounceCooldownMs(long p0) {
    }
    
    public final void processResult(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.ml.GestureResult result) {
    }
}