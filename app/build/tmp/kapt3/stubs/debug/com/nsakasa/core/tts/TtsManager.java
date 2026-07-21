package com.nsakasa.core.tts;

/**
 * Manages Android TextToSpeech engine lifecycle and speech playback.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0006\u0010\u001b\u001a\u00020\u0018J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0018R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\f@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/nsakasa/core/tts/TtsManager;", "Landroid/speech/tts/TextToSpeech$OnInitListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isInitialized", "", "value", "isSpeechEnabled", "()Z", "setSpeechEnabled", "(Z)V", "", "pitch", "getPitch", "()F", "setPitch", "(F)V", "speechRate", "getSpeechRate", "setSpeechRate", "tts", "Landroid/speech/tts/TextToSpeech;", "onInit", "", "status", "", "shutdown", "speak", "text", "", "stop", "Companion", "app_debug"})
public final class TtsManager implements android.speech.tts.TextToSpeech.OnInitListener {
    @org.jetbrains.annotations.Nullable()
    private android.speech.tts.TextToSpeech tts;
    private boolean isInitialized = false;
    private boolean isSpeechEnabled = true;
    private float speechRate = 1.0F;
    private float pitch = 1.0F;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "TtsManager";
    @org.jetbrains.annotations.NotNull()
    public static final com.nsakasa.core.tts.TtsManager.Companion Companion = null;
    
    public TtsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isSpeechEnabled() {
        return false;
    }
    
    public final void setSpeechEnabled(boolean value) {
    }
    
    public final float getSpeechRate() {
        return 0.0F;
    }
    
    public final void setSpeechRate(float value) {
    }
    
    public final float getPitch() {
        return 0.0F;
    }
    
    public final void setPitch(float value) {
    }
    
    @java.lang.Override()
    public void onInit(int status) {
    }
    
    public final void speak(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void stop() {
    }
    
    public final void shutdown() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/nsakasa/core/tts/TtsManager$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}