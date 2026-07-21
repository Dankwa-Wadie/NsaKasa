package com.nsakasa.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastWhite
import com.nsakasa.ui.theme.HighContrastYellow
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val confidenceThreshold by viewModel.confidenceThreshold.collectAsState()
    val speechRate by viewModel.speechRate.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Settings & Audio Options",
            style = MaterialTheme.typography.headlineMedium,
            color = HighContrastYellow,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Card 1: Confidence Threshold
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            color = DarkSurface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "CLASSIFICATION THRESHOLD",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = HighContrastCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Confidence Cutoff:",
                        fontSize = 18.sp,
                        color = HighContrastWhite
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${(confidenceThreshold * 100).roundToInt()}%",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = HighContrastGreen
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Slider(
                    value = confidenceThreshold,
                    onValueChange = { viewModel.updateConfidenceThreshold(it) },
                    valueRange = 0.50f..0.95f,
                    steps = 8,
                    colors = SliderDefaults.colors(
                        thumbColor = HighContrastGreen,
                        activeTrackColor = HighContrastGreen,
                        inactiveTrackColor = DarkBackground
                    ),
                    modifier = Modifier.semantics {
                        contentDescription = "Gesture classification confidence threshold slider, currently ${(confidenceThreshold * 100).roundToInt()} percent"
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 2: Speech Rate
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            color = DarkSurface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "TEXT TO SPEECH SPEED",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = HighContrastCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Voice Playback Rate:",
                        fontSize = 18.sp,
                        color = HighContrastWhite
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("%.1fx", speechRate),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = HighContrastYellow
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Slider(
                    value = speechRate,
                    onValueChange = { viewModel.updateSpeechRate(it) },
                    valueRange = 0.5f..2.0f,
                    steps = 5,
                    colors = SliderDefaults.colors(
                        thumbColor = HighContrastYellow,
                        activeTrackColor = HighContrastYellow,
                        inactiveTrackColor = DarkBackground
                    ),
                    modifier = Modifier.semantics {
                        contentDescription = "Text to speech playback rate slider, currently ${String.format("%.1f", speechRate)} times speed"
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 3: Hackathon & App Info
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            color = DarkSurface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "ABOUT NSA KASA",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = HighContrastCyan
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "MTN Ghana Tɛkyerɛma Pa Hackathon 2026",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HighContrastYellow
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Nsa Kasa (Hand Speaks) translates Ghanaian Sign Language (GSL) into spoken/written text and transcribes speech in real time for accessible communication.",
                    fontSize = 16.sp,
                    color = HighContrastWhite,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Architecture Phase 7 Ready: MediaPipe landmark stream Flow<HandLandmarkResult> is decoupled for future Godot Skeleton3D/Bone3D JNI bridge embedding.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    lineHeight = 20.sp
                )
            }
        }
    }
}
