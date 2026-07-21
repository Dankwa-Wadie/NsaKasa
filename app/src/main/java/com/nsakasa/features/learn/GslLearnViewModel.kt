package com.nsakasa.features.learn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.camera.HandLandmarkResult
import com.nsakasa.core.ml.GestureClassifierInterface
import com.nsakasa.core.ml.GestureResult
import com.nsakasa.features.learn.model.GslDataset
import com.nsakasa.features.learn.model.GslSignItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GslLearnViewModel @Inject constructor(
    private val gestureClassifier: GestureClassifierInterface
) : ViewModel() {

    // Tab Selection: 0 = Study Catalog & 3D Model, 1 = Hand Guess Practice Challenge
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    // Categories filter: "All", "Alphabet (A-Z)", "Greetings", "Essentials"
    private val _selectedCategory = MutableStateFlow("Alphabet (A-Z)")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val allCategories = listOf("Alphabet (A-Z)", "Greetings", "Essentials", "All")

    // Filtered signs list based on category & search
    val filteredSigns: StateFlow<List<GslSignItem>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        GslDataset.allSigns.filter { item ->
            val matchesCategory = (category == "All" || item.category == category)
            val matchesQuery = query.isBlank() ||
                    item.label.contains(query, ignoreCase = true) ||
                    item.twiTranslation.contains(query, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, GslDataset.alphabetSigns)

    // Currently selected sign for 3D inspection
    private val _selectedSign = MutableStateFlow(GslDataset.alphabetSigns.first())
    val selectedSign: StateFlow<GslSignItem> = _selectedSign.asStateFlow()

    // Camera Hand Landmark & Gesture Classification flows
    private val _landmarkResult = MutableStateFlow<HandLandmarkResult?>(null)
    val landmarkResult: StateFlow<HandLandmarkResult?> = _landmarkResult.asStateFlow()

    val gestureResult: StateFlow<GestureResult> = gestureClassifier.gestureResultFlow

    // Hand Guess Challenge state
    private var challengeIndex = 0
    private val challengeList = GslDataset.allSigns

    private val _targetChallengeSign = MutableStateFlow(challengeList[0])
    val targetChallengeSign: StateFlow<GslSignItem> = _targetChallengeSign.asStateFlow()

    private val _challengeScore = MutableStateFlow(0)
    val challengeScore: StateFlow<Int> = _challengeScore.asStateFlow()

    private val _challengeStreak = MutableStateFlow(0)
    val challengeStreak: StateFlow<Int> = _challengeStreak.asStateFlow()

    private val _guessStatusMessage = MutableStateFlow("Place hand in camera view & perform the sign!")
    val guessStatusMessage: StateFlow<String> = _guessStatusMessage.asStateFlow()

    private val _isMatchSuccess = MutableStateFlow(false)
    val isMatchSuccess: StateFlow<Boolean> = _isMatchSuccess.asStateFlow()

    private var hasPassedCurrentChallenge = false

    init {
        // Monitor live gesture recognition during Hand Guess Challenge
        viewModelScope.launch {
            gestureResult.collect { result ->
                if (_selectedTab.value == 1 && !hasPassedCurrentChallenge) {
                    evaluateHandGuess(result)
                }
            }
        }
    }

    fun setSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectSign(item: GslSignItem) {
        _selectedSign.value = item
    }

    fun updateLandmarkResult(result: HandLandmarkResult) {
        viewModelScope.launch {
            _landmarkResult.emit(result)
            gestureClassifier.processLandmarks(result)
        }
    }

    private fun evaluateHandGuess(result: GestureResult) {
        val target = _targetChallengeSign.value
        val labelMatches = result.label.contains(target.label, ignoreCase = true) ||
                (target.label.startsWith("Letter ") && result.label.contains(target.label.removePrefix("Letter "), ignoreCase = true))

        if (labelMatches && result.confidence >= 0.70f) {
            hasPassedCurrentChallenge = true
            _isMatchSuccess.value = true
            _challengeScore.value += 1
            _challengeStreak.value += 1
            _guessStatusMessage.value = "EXCELLENT! Matched ${target.label} (${(result.confidence * 100).toInt()}%)"
        } else if (result.confidence >= 0.5f && result.label != "No Hand Detected") {
            _guessStatusMessage.value = "Detected: ${result.label}. Keep trying for ${target.label}!"
        }
    }

    fun nextChallenge() {
        hasPassedCurrentChallenge = false
        _isMatchSuccess.value = false
        challengeIndex = (challengeIndex + 1) % challengeList.size
        _targetChallengeSign.value = challengeList[challengeIndex]
        _guessStatusMessage.value = "Perform: ${_targetChallengeSign.value.label}"
    }

    fun simulateCorrectGuess() {
        if (!hasPassedCurrentChallenge) {
            hasPassedCurrentChallenge = true
            _isMatchSuccess.value = true
            _challengeScore.value += 1
            _challengeStreak.value += 1
            _guessStatusMessage.value = "GREAT JOB! Matched ${_targetChallengeSign.value.label} (94%)"
        }
    }

    override fun onCleared() {
        super.onCleared()
        gestureClassifier.close()
    }
}
