package com.nsakasa.features.speechtranslate

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastYellow
import com.nsakasa.ui.theme.JointPointColor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeechTranslateScreen(
    viewModel: SpeechTranslateViewModel = hiltViewModel()
) {
    val micPermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)
    val transcribedText by viewModel.transcribedText.collectAsState()
    val isListening by viewModel.isListening.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Text(
            text = "Speech to Text Mode",
            style = MaterialTheme.typography.headlineMedium,
            color = HighContrastYellow,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "Speak into microphone for deaf user to read",
            fontSize = 16.sp,
            color = HighContrastCyan,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        if (micPermissionState.status.isGranted) {
            // Main Live Transcription Card (Accessible 28sp+ Large Text Display)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp)),
                color = DarkSurface
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (transcribedText.isBlank()) {
                            Text(
                                text = if (isListening) "Listening... Speak now" else "Tap microphone below and start speaking...",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                                textAlign = TextAlign.Center,
                                lineHeight = 34.sp
                            )
                        } else {
                            Text(
                                text = transcribedText,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = HighContrastYellow,
                                textAlign = TextAlign.Center,
                                lineHeight = 42.sp,
                                modifier = Modifier.semantics {
                                    contentDescription = "Live speech transcription: $transcribedText"
                                }
                            )
                        }

                        if (!errorState.isNullOrEmpty()) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = errorState ?: "",
                                fontSize = 18.sp,
                                color = JointPointColor,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action Microphone Control Button (WCAG AA 64x64dp touch target)
            Button(
                onClick = { viewModel.toggleListening() },
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .semantics {
                        contentDescription = if (isListening) "Stop listening" else "Start speech to text recording"
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isListening) JointPointColor else HighContrastGreen,
                    contentColor = DarkBackground
                )
            ) {
                Icon(
                    imageVector = if (isListening) Icons.Default.MicOff else Icons.Default.Mic,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (isListening) "TAP TO STOP" else "TAP TO SPEAK",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isListening) JointPointColor else HighContrastGreen
            )

            Spacer(modifier = Modifier.height(16.dp))

        } else {
            // Permission Banner
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Microphone Permission Required",
                    style = MaterialTheme.typography.headlineMedium,
                    color = HighContrastYellow,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Nsa Kasa needs audio recording access to transcribe spoken English into text for deaf users.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { micPermissionState.launchPermissionRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 200.dp, height = 56.dp)
                        .semantics {
                            contentDescription = "Grant microphone permission button"
                        },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = HighContrastYellow,
                        contentColor = DarkBackground
                    )
                ) {
                    Text(
                        text = "Grant Permission",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
