package com.nsakasa.features.conversationlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsakasa.core.data.ConversationDao
import com.nsakasa.core.data.ConversationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationLogViewModel @Inject constructor(
    private val conversationDao: ConversationDao
) : ViewModel() {

    val conversations: StateFlow<List<ConversationEntity>> = conversationDao
        .getAllConversations()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun clearHistory() {
        viewModelScope.launch {
            conversationDao.clearAll()
        }
    }
}
