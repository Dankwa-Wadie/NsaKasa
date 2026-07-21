package com.nsakasa.features.speechtranslate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.data.ConversationDao
import com.nsakasa.core.data.ConversationEntity
import com.nsakasa.core.stt.SttManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeechTranslateViewModel @Inject constructor(
    private val sttManager: SttManager,
    private val conversationDao: ConversationDao
) : ViewModel() {

    val transcribedText: StateFlow<String> = sttManager.transcribedText
    val isListening: StateFlow<Boolean> = sttManager.isListening
    val errorState: StateFlow<String?> = sttManager.errorState

    fun toggleListening() {
        if (isListening.value) {
            sttManager.stopListening()
            saveTranscription()
        } else {
            sttManager.startListening()
        }
    }

    fun saveTranscription() {
        val text = transcribedText.value.trim()
        if (text.isNotBlank()) {
            viewModelScope.launch {
                conversationDao.insert(
                    ConversationEntity(
                        type = "SPEECH_TO_TEXT",
                        text = text,
                        confidence = 1.0f,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        sttManager.destroy()
    }
}
