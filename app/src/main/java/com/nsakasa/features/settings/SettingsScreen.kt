package com.nsakasa.features.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nsakasa.core.ota.OtaUpdateState
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastWhite
import com.nsakasa.ui.theme.HighContrastYellow
import com.nsakasa.ui.theme.JointPointColor
import kotlin.math.roundToInt

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val confidenceThreshold by viewModel.confidenceThreshold.collectAsState()
    val speechRate by viewModel.speechRate.collectAsState()
    val otaState by viewModel.otaState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Settings & OTA Options",
            style = MaterialTheme.typography.headlineMedium,
            color = HighContrastYellow,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Card 1: Over-The-Air (OTA) Application Auto-Updater
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            color = DarkSurface
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.SystemUpdate,
                        contentDescription = null,
                        tint = HighContrastYellow,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "OVER-THE-AIR (OTA) UPDATE",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = HighContrastCyan
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Current App Version: v1.0.0",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = HighContrastWhite
                )

                Spacer(modifier = Modifier.height(12.dp))

                when (val state = otaState) {
                    is OtaUpdateState.Idle -> {
                        Button(
                            onClick = { viewModel.checkForAppUpdates() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .semantics { contentDescription = "Check for app update button" },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HighContrastYellow,
                                contentColor = DarkBackground
                            )
                        ) {
                            Text("Check for Updates", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    is OtaUpdateState.Checking -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = HighContrastYellow,
                                strokeWidth = 3.dp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Checking GitHub for updates...", fontSize = 16.sp, color = HighContrastWhite)
                        }
                    }
                    is OtaUpdateState.UpdateAvailable -> {
                        Column {
                            Text(
                                text = "New Version Available: ${state.versionName}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = HighContrastGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = state.releaseNotes,
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { viewModel.downloadUpdateApk(state.apkDownloadUrl) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .semantics { contentDescription = "Download APK update button" },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = HighContrastGreen,
                                    contentColor = DarkBackground
                                )
                            ) {
                                Text("Download Update APK", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    is OtaUpdateState.NoUpdateAvailable -> {
                        Column {
                            Text("App is up to date!", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = HighContrastGreen)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { viewModel.checkForAppUpdates() },
                                modifier = Modifier.fillMaxWidth().height(44.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = DarkBackground, contentColor = HighContrastCyan)
                            ) {
                                Text("Re-check Updates", fontSize = 14.sp)
                            }
                        }
                    }
                    is OtaUpdateState.Downloading -> {
                        Column {
                            val percent = (state.progress * 100).roundToInt()
                            Text("Downloading update: $percent%", fontSize = 16.sp, color = HighContrastYellow)
                            Spacer(modifier = Modifier.height(8.dp))
                            LinearProgressIndicator(
                                progress = { state.progress },
                                modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                                color = HighContrastGreen,
                                trackColor = DarkBackground
                            )
                        }
                    }
                    is OtaUpdateState.ReadyToInstall -> {
                        Column {
                            Text("Update downloaded successfully!", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = HighContrastGreen)
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = { viewModel.installUpdate(context, state.apkFile) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)
                                    .semantics { contentDescription = "Install update now button" },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = HighContrastGreen,
                                    contentColor = DarkBackground
                                )
                            ) {
                                Text("INSTALL UPDATE NOW", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                    is OtaUpdateState.Error -> {
                        Column {
                            Text(text = state.message, fontSize = 15.sp, color = JointPointColor)
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { viewModel.checkForAppUpdates() },
                                modifier = Modifier.fillMaxWidth().height(44.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = HighContrastYellow, contentColor = DarkBackground)
                            ) {
                                Text("Try Again", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Card 2: Confidence Threshold
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

        // Card 3: Speech Rate
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

        // Card 4: Hackathon & App Info
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
