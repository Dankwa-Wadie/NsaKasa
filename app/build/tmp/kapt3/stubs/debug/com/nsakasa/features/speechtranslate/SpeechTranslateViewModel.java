package com.nsakasa.features.speechtranslate;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Lcom/nsakasa/features/speechtranslate/SpeechTranslateViewModel;", "Landroidx/lifecycle/ViewModel;", "sttManager", "Lcom/nsakasa/core/stt/SttManager;", "conversationDao", "Lcom/nsakasa/core/data/ConversationDao;", "(Lcom/nsakasa/core/stt/SttManager;Lcom/nsakasa/core/data/ConversationDao;)V", "errorState", "Lkotlinx/coroutines/flow/StateFlow;", "", "getErrorState", "()Lkotlinx/coroutines/flow/StateFlow;", "isListening", "", "transcribedText", "getTranscribedText", "onCleared", "", "saveTranscription", "toggleListening", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SpeechTranslateViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.stt.SttManager sttManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.data.ConversationDao conversationDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> transcribedText = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isListening = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorState = null;
    
    @javax.inject.Inject()
    public SpeechTranslateViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.stt.SttManager sttManager, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.data.ConversationDao conversationDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getTranscribedText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isListening() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorState() {
        return null;
    }
    
    public final void toggleListening() {
    }
    
    public final void saveTranscription() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}