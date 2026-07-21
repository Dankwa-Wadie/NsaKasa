package com.nsakasa.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversations")
data class ConversationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String, // "GESTURE_TO_SPEECH" or "SPEECH_TO_TEXT"
    val text: String,
    val confidence: Float = 1.0f,
    val timestamp: Long = System.currentTimeMillis()
)
