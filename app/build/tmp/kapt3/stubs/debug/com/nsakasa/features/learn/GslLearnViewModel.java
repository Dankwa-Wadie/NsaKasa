package com.nsakasa.features.learn;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020&H\u0002J\u0006\u0010;\u001a\u000209J\b\u0010<\u001a\u000209H\u0014J\u0006\u0010=\u001a\u000209J\u0006\u0010>\u001a\u000209J\u000e\u0010?\u001a\u0002092\u0006\u0010@\u001a\u00020\u0014J\u000e\u0010A\u001a\u0002092\u0006\u0010B\u001a\u00020\fJ\u000e\u0010C\u001a\u0002092\u0006\u0010D\u001a\u00020\fJ\u000e\u0010E\u001a\u0002092\u0006\u0010F\u001a\u00020\tJ\u0006\u0010G\u001a\u000209J\u000e\u0010H\u001a\u0002092\u0006\u0010:\u001a\u00020\u0010R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00180\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010 R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u000e\u0010*\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\f0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00140\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010 R\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\u00140\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006I"}, d2 = {"Lcom/nsakasa/features/learn/GslLearnViewModel;", "Landroidx/lifecycle/ViewModel;", "gestureClassifier", "Lcom/nsakasa/core/ml/GestureClassifierInterface;", "ttsManager", "Lcom/nsakasa/core/tts/TtsManager;", "(Lcom/nsakasa/core/ml/GestureClassifierInterface;Lcom/nsakasa/core/tts/TtsManager;)V", "_challengeScore", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_challengeStreak", "_guessStatusMessage", "", "_isMatchSuccess", "", "_landmarkResult", "Lcom/nsakasa/core/camera/HandLandmarkResult;", "_searchQuery", "_selectedCategory", "_selectedSign", "Lcom/nsakasa/features/learn/model/GslSignItem;", "_selectedTab", "_targetChallengeSign", "allCategories", "", "getAllCategories", "()Ljava/util/List;", "challengeIndex", "challengeList", "challengeScore", "Lkotlinx/coroutines/flow/StateFlow;", "getChallengeScore", "()Lkotlinx/coroutines/flow/StateFlow;", "challengeStreak", "getChallengeStreak", "filteredSigns", "getFilteredSigns", "gestureResult", "Lcom/nsakasa/core/ml/GestureResult;", "getGestureResult", "guessStatusMessage", "getGuessStatusMessage", "hasPassedCurrentChallenge", "isMatchSuccess", "landmarkResult", "getLandmarkResult", "searchQuery", "getSearchQuery", "selectedCategory", "getSelectedCategory", "selectedSign", "getSelectedSign", "selectedTab", "getSelectedTab", "targetChallengeSign", "getTargetChallengeSign", "evaluateHandGuess", "", "result", "nextChallenge", "onCleared", "onEnterLearnMode", "onExitLearnMode", "selectSign", "item", "setSearchQuery", "query", "setSelectedCategory", "category", "setSelectedTab", "index", "simulateCorrectGuess", "updateLandmarkResult", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GslLearnViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier = null;
    @org.jetbrains.annotations.NotNull()
    private final com.nsakasa.core.tts.TtsManager ttsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _selectedTab = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedTab = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> selectedCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> allCategories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.nsakasa.features.learn.model.GslSignItem>> filteredSigns = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.features.learn.model.GslSignItem> _selectedSign = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.features.learn.model.GslSignItem> selectedSign = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.camera.HandLandmarkResult> _landmarkResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.camera.HandLandmarkResult> landmarkResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ml.GestureResult> gestureResult = null;
    private int challengeIndex = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.nsakasa.features.learn.model.GslSignItem> challengeList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.features.learn.model.GslSignItem> _targetChallengeSign = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.features.learn.model.GslSignItem> targetChallengeSign = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _challengeScore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> challengeScore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _challengeStreak = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> challengeStreak = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _guessStatusMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> guessStatusMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isMatchSuccess = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isMatchSuccess = null;
    private boolean hasPassedCurrentChallenge = false;
    
    @javax.inject.Inject()
    public GslLearnViewModel(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.ml.GestureClassifierInterface gestureClassifier, @org.jetbrains.annotations.NotNull()
    com.nsakasa.core.tts.TtsManager ttsManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSelectedTab() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSelectedCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAllCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.nsakasa.features.learn.model.GslSignItem>> getFilteredSigns() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.features.learn.model.GslSignItem> getSelectedSign() {
        return null;
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
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.features.learn.model.GslSignItem> getTargetChallengeSign() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getChallengeScore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getChallengeStreak() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getGuessStatusMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isMatchSuccess() {
        return null;
    }
    
    public final void onEnterLearnMode() {
    }
    
    public final void onExitLearnMode() {
    }
    
    public final void setSelectedTab(int index) {
    }
    
    public final void setSelectedCategory(@org.jetbrains.annotations.NotNull()
    java.lang.String category) {
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void selectSign(@org.jetbrains.annotations.NotNull()
    com.nsakasa.features.learn.model.GslSignItem item) {
    }
    
    public final void updateLandmarkResult(@org.jetbrains.annotations.NotNull()
    com.nsakasa.core.camera.HandLandmarkResult result) {
    }
    
    private final void evaluateHandGuess(com.nsakasa.core.ml.GestureResult result) {
    }
    
    public final void nextChallenge() {
    }
    
    public final void simulateCorrectGuess() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}