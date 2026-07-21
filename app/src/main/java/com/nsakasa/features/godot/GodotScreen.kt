package com.nsakasa.features.godot

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ViewInAr
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nsakasa.core.camera.LandmarkPoint
import com.nsakasa.features.learn.model.GslDataset
import com.nsakasa.ui.components.Hand3DVisualizer
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GodotScreen(
    viewModel: GodotViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    var isZoomedIn by remember { mutableStateOf(false) }

    val gestures = listOf(
        "AKWAABA",
        "THANK_YOU",
        "LETTER_A",
        "LETTER_B",
        "LETTER_C",
        "OPEN_PALM",
        "CLOSED_FIST",
        "POINTING",
        "THUMBS_UP"
    )

    // Maps gesture names to 3D Landmark positions for the 3D Avatar
    val gesturePoses = remember {
        mapOf(
            "AKWAABA" to GslDataset.allSigns.first { it.id == "gsl_akwaaba" }.landmarks3D,
            "THANK_YOU" to GslDataset.allSigns.first { it.id == "gsl_thank_you" }.landmarks3D,
            "LETTER_A" to GslDataset.allSigns.first { it.id == "alpha_a" }.landmarks3D,
            "LETTER_B" to GslDataset.allSigns.first { it.id == "alpha_b" }.landmarks3D,
            "LETTER_C" to GslDataset.allSigns.first { it.id == "alpha_c" }.landmarks3D,
            "OPEN_PALM" to GslDataset.allSigns.first { it.id == "gsl_thank_you" }.landmarks3D,
            "CLOSED_FIST" to GslDataset.allSigns.first { it.id == "alpha_s" }.landmarks3D,
            "POINTING" to GslDataset.allSigns.first { it.id == "alpha_d" }.landmarks3D,
            "THUMBS_UP" to GslDataset.allSigns.first { it.id == "alpha_a" }.landmarks3D
        )
    }

    val currentLandmarks = gesturePoses[state.currentGesture] ?: gesturePoses["AKWAABA"]!!

    LaunchedEffect(Unit) {
        viewModel.onEngineStarted()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.ViewInAr,
                            contentDescription = null,
                            tint = HighContrastYellow,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Godot 3D Sign Avatar",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = HighContrastYellow
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Interactive 3D Avatar Viewport Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkSurface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // Render 3D Avatar Pose in real time
                    Hand3DVisualizer(
                        landmarks = currentLandmarks,
                        modifier = Modifier.fillMaxSize(),
                        autoRotate = true
                    )

                    // Top status overlay inside 3D viewport
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(DarkBackground.copy(alpha = 0.85f))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = HighContrastGreen,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isZoomedIn) "🔍 Zoomed to Hands: GSL Learning (${state.currentGesture})" else "3D Avatar Idle Breathing (Wave Greeting Ready)",
                                color = HighContrastGreen,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Bottom Camera Zoom Controls Bar
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(12.dp)
                            .background(DarkBackground.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 8.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                isZoomedIn = true
                                viewModel.onGestureSelected("AKWAABA")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isZoomedIn) HighContrastYellow else DarkSurface,
                                contentColor = if (isZoomedIn) DarkBackground else HighContrastYellow
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Icon(Icons.Default.ZoomIn, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Zoom to Hands", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = {
                                isZoomedIn = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!isZoomedIn) HighContrastYellow else DarkSurface,
                                contentColor = if (!isZoomedIn) DarkBackground else HighContrastYellow
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Full Body View", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = {
                                viewModel.onGestureSelected("AKWAABA")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkSurface,
                                contentColor = HighContrastCyan
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.height(36.dp)
                        ) {
                            Icon(Icons.Default.Handshake, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Wave", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Interactive Gesture Trigger Controls
            Text(
                text = "SELECT GSL SIGN TO DEMONSTRATE (AUTO-ZOOM)",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = HighContrastCyan,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(gestures) { gesture ->
                    val isSelected = state.currentGesture == gesture
                    Surface(
                        onClick = {
                            isZoomedIn = true
                            viewModel.onGestureSelected(gesture)
                        },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) HighContrastYellow else DarkSurface,
                        border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, HighContrastCyan)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    tint = DarkBackground,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                            Text(
                                text = gesture.replace("_", " "),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) DarkBackground else HighContrastCyan
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Custom 3D Model Upload Notice Card
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.FileUpload,
                        contentDescription = null,
                        tint = HighContrastYellow,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Custom 3D Avatar Model (avatar.glb)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = HighContrastYellow
                        )
                        Text(
                            text = "Idle breathing & waving initialized. Tapping any GSL sign automatically zooms into the hands to teach GSL.",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}
