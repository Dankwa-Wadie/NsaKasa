package com.nsakasa.features.conversationlog

import android.text.format.DateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Hearing
import androidx.compose.material.icons.filled.PanTool
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nsakasa.core.data.ConversationEntity
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastWhite
import com.nsakasa.ui.theme.HighContrastYellow
import com.nsakasa.ui.theme.JointPointColor
import java.util.Date

@Composable
fun ConversationLogScreen(
    viewModel: ConversationLogViewModel = hiltViewModel()
) {
    val conversations by viewModel.conversations.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(16.dp)
    ) {
        // Top Header with Clear Action
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Conversation Log",
                    style = MaterialTheme.typography.headlineMedium,
                    color = HighContrastYellow
                )
                Text(
                    text = "${conversations.size} saved exchanges",
                    fontSize = 16.sp,
                    color = HighContrastCyan
                )
            }

            if (conversations.isNotEmpty()) {
                IconButton(
                    onClick = { showDeleteDialog = true },
                    modifier = Modifier.semantics {
                        contentDescription = "Clear all conversation log history"
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteSweep,
                        contentDescription = null,
                        tint = JointPointColor,
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }
            }
        }

        if (conversations.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No saved conversations yet.\nTranslated gestures and speech transcriptions will appear here.",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    lineHeight = 28.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(conversations, key = { it.id }) { item ->
                    ConversationItemCard(item)
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Clear History?",
                    color = HighContrastYellow,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete all saved conversation logs? This action cannot be undone.",
                    color = HighContrastWhite,
                    fontSize = 18.sp
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.clearHistory()
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = JointPointColor)
                ) {
                    Text("Delete All", color = HighContrastWhite, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel", color = HighContrastCyan, fontSize = 18.sp)
                }
            },
            containerColor = DarkSurface
        )
    }
}

@Composable
fun ConversationItemCard(item: ConversationEntity) {
    val isGesture = item.type == "GESTURE_TO_SPEECH"
    val formattedTime = remember(item.timestamp) {
        DateFormat.format("MMM dd, yyyy - hh:mm a", Date(item.timestamp)).toString()
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
        color = DarkSurface
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isGesture) Icons.Default.PanTool else Icons.Default.Hearing,
                        contentDescription = null,
                        tint = if (isGesture) HighContrastYellow else HighContrastGreen,
                        modifier = Modifier.height(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isGesture) "GESTURE TRANSLATION" else "SPEECH TRANSCRIPTION",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isGesture) HighContrastYellow else HighContrastGreen
                    )
                }

                Text(
                    text = formattedTime,
                    fontSize = 13.sp,
                    color = HighContrastCyan
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = item.text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = HighContrastWhite,
                lineHeight = 28.sp,
                modifier = Modifier.semantics {
                    contentDescription = "${if (isGesture) "Gesture translation" else "Speech transcription"}: ${item.text} at $formattedTime"
                }
            )
        }
    }
}
