package com.nsakasa.features.learn

import android.Manifest
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CameraFront
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nsakasa.core.camera.HandTrackingAnalyzer
import com.nsakasa.features.learn.model.GslSignItem
import com.nsakasa.ui.components.Hand3DVisualizer
import com.nsakasa.ui.components.LandmarkOverlay
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastYellow
import kotlin.math.roundToInt

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GslLearnScreen(
    viewModel: GslLearnViewModel = hiltViewModel()
) {
    val selectedTab by viewModel.selectedTab.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredSigns by viewModel.filteredSigns.collectAsState()
    val selectedSign by viewModel.selectedSign.collectAsState()

    val targetChallengeSign by viewModel.targetChallengeSign.collectAsState()
    val challengeScore by viewModel.challengeScore.collectAsState()
    val challengeStreak by viewModel.challengeStreak.collectAsState()
    val guessStatusMessage by viewModel.guessStatusMessage.collectAsState()
    val isMatchSuccess by viewModel.isMatchSuccess.collectAsState()

    val landmarkResult by viewModel.landmarkResult.collectAsState()
    val gestureResult by viewModel.gestureResult.collectAsState()

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        // Screen Header & Tab Selector
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkSurface)
                .padding(top = 12.dp, bottom = 4.dp)
        ) {
            Column {
                Text(
                    text = "GSL HAND GUESSES & LEARN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = HighContrastYellow,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .semantics {
                            contentDescription = "Ghanaian Sign Language Hand Guesses and Learning Title"
                        }
                )

                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = DarkSurface,
                    contentColor = HighContrastYellow,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                            color = HighContrastYellow
                        )
                    }
                ) {
                    Tab(
                        selected = selectedTab == 0,
                        onClick = { viewModel.setSelectedTab(0) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("3D Study Catalog", fontWeight = FontWeight.Bold)
                            }
                        },
                        selectedContentColor = HighContrastYellow,
                        unselectedContentColor = HighContrastCyan
                    )

                    Tab(
                        selected = selectedTab == 1,
                        onClick = { viewModel.setSelectedTab(1) },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Psychology,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Hand Guess Practice", fontWeight = FontWeight.Bold)
                            }
                        },
                        selectedContentColor = HighContrastYellow,
                        unselectedContentColor = HighContrastCyan
                    )
                }
            }
        }

        if (selectedTab == 0) {
            // Mode 0: 3D Study Catalog
            StudyCatalogContent(
                selectedCategory = selectedCategory,
                allCategories = viewModel.allCategories,
                searchQuery = searchQuery,
                filteredSigns = filteredSigns,
                selectedSign = selectedSign,
                onCategorySelect = { viewModel.setSelectedCategory(it) },
                onSearchChange = { viewModel.setSearchQuery(it) },
                onSignSelect = { viewModel.selectSign(it) },
                onTestSignClick = { sign ->
                    viewModel.selectSign(sign)
                    viewModel.setSelectedTab(1)
                }
            )
        } else {
            // Mode 1: Hand Guess Test Challenge
            HandGuessTestContent(
                cameraPermissionState = cameraPermissionState,
                context = context,
                lifecycleOwner = lifecycleOwner,
                targetSign = targetChallengeSign,
                score = challengeScore,
                streak = challengeStreak,
                statusMessage = guessStatusMessage,
                isSuccess = isMatchSuccess,
                landmarkResult = landmarkResult,
                gestureResult = gestureResult,
                onLandmarkUpdate = { viewModel.updateLandmarkResult(it) },
                onNextChallenge = { viewModel.nextChallenge() },
                onSimulateGuess = { viewModel.simulateCorrectGuess() }
            )
        }
    }
}

@Composable
private fun StudyCatalogContent(
    selectedCategory: String,
    allCategories: List<String>,
    searchQuery: String,
    filteredSigns: List<GslSignItem>,
    selectedSign: GslSignItem,
    onCategorySelect: (String) -> Unit,
    onSearchChange: (String) -> Unit,
    onSignSelect: (GslSignItem) -> Unit,
    onTestSignClick: (GslSignItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        // Category Pills
        item {
            Spacer(modifier = Modifier.height(12.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(allCategories) { category ->
                    val isSelected = category == selectedCategory
                    Surface(
                        onClick = { onCategorySelect(category) },
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) HighContrastYellow else DarkSurface,
                        border = if (isSelected) null else androidx.compose.foundation.BorderStroke(1.dp, HighContrastCyan)
                    ) {
                        Text(
                            text = category,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) DarkBackground else HighContrastCyan,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // Search Input
        item {
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                placeholder = { Text("Search Alphabet (A-Z) or GSL phrases...", color = HighContrastCyan.copy(alpha = 0.6f)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = HighContrastYellow) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = HighContrastYellow,
                    unfocusedBorderColor = HighContrastCyan,
                    focusedContainerColor = DarkSurface,
                    unfocusedContainerColor = DarkSurface
                ),
                shape = RoundedCornerShape(12.dp)
            )
        }

        // Interactive 3D Hand Inspector Model
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = selectedSign.label,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = HighContrastYellow
                            )
                            Text(
                                text = "Twi: ${selectedSign.twiTranslation}",
                                fontSize = 14.sp,
                                color = HighContrastCyan,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Surface(
                            color = HighContrastGreen,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = selectedSign.category,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkBackground,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 3D Visualizer Canvas
                    Hand3DVisualizer(
                        landmarks = selectedSign.landmarks3D,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        autoRotate = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "HAND POSTURE GUIDE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = HighContrastCyan
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = selectedSign.description,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkBackground, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = null,
                            tint = HighContrastYellow,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tip: ${selectedSign.practiceTip}",
                            fontSize = 13.sp,
                            color = HighContrastYellow,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { onTestSignClick(selectedSign) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = HighContrastYellow,
                            contentColor = DarkBackground
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.CameraFront, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Test My Sign in Camera", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
        }

        // GSL Grid / Cards Title
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "SELECT A SIGN TO STUDY IN 3D (${filteredSigns.size})",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = HighContrastCyan
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

        // List of Sign Cards
        items(filteredSigns) { sign ->
            val isSelected = sign.id == selectedSign.id
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) HighContrastYellow.copy(alpha = 0.15f) else DarkSurface
                ),
                border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, HighContrastYellow) else null,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onSignSelect(sign) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = sign.label,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) HighContrastYellow else MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Twi: ${sign.twiTranslation}",
                            fontSize = 13.sp,
                            color = HighContrastCyan
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Inspect ${sign.label} in 3D",
                        tint = if (isSelected) HighContrastYellow else HighContrastCyan
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HandGuessTestContent(
    cameraPermissionState: com.google.accompanist.permissions.PermissionState,
    context: android.content.Context,
    lifecycleOwner: androidx.lifecycle.LifecycleOwner,
    targetSign: GslSignItem,
    score: Int,
    streak: Int,
    statusMessage: String,
    isSuccess: Boolean,
    landmarkResult: com.nsakasa.core.camera.HandLandmarkResult?,
    gestureResult: com.nsakasa.core.ml.GestureResult,
    onLandmarkUpdate: (com.nsakasa.core.camera.HandLandmarkResult) -> Unit,
    onNextChallenge: () -> Unit,
    onSimulateGuess: () -> Unit
) {
    var isFrontCamera by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        if (cameraPermissionState.status.isGranted) {
            val analyzer = remember { HandTrackingAnalyzer(context) }

            LaunchedEffect(analyzer) {
                analyzer.resultFlow.collect { result ->
                    onLandmarkUpdate(result)
                }
            }

            DisposableEffect(Unit) {
                onDispose { analyzer.close() }
            }

            // Camera View
            AndroidView(
                factory = { ctx ->
                    PreviewView(ctx).apply { scaleType = PreviewView.ScaleType.FILL_CENTER }
                },
                update = { previewView ->
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                            .also {
                                it.setAnalyzer(ContextCompat.getMainExecutor(context), analyzer)
                            }

                        val cameraSelector = if (isFrontCamera) {
                            CameraSelector.DEFAULT_FRONT_CAMERA
                        } else {
                            CameraSelector.DEFAULT_BACK_CAMERA
                        }

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalysis
                            )
                        } catch (e: Exception) {
                            Log.e("HandGuessTestContent", "Camera bind failed", e)
                        }
                    }, ContextCompat.getMainExecutor(context))
                },
                modifier = Modifier.fillMaxSize()
            )

            // Landmark Overlay
            LandmarkOverlay(
                landmarkResult = landmarkResult,
                isFrontCamera = isFrontCamera,
                modifier = Modifier.fillMaxSize()
            )

            // Top Challenge Banner & Score Tracker
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkBackground.copy(alpha = 0.90f)),
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "CHALLENGE: PERFORM SIGN",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = HighContrastCyan
                            )
                            Text(
                                text = targetSign.label,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = HighContrastYellow
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = DarkSurface,
                                shape = RoundedCornerShape(12.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, HighContrastYellow)
                            ) {
                                Text(
                                    text = "Score: $score • Streak: $streak 🔥",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = HighContrastYellow,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = targetSign.description,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Bottom Real-time Hand Guess Evaluation & Control Card
            Card(
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isSuccess) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = HighContrastGreen,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = statusMessage,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = HighContrastGreen
                            )
                        }
                    } else {
                        Text(
                            text = statusMessage,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = HighContrastYellow,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = onNextChallenge,
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = HighContrastYellow,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Next Challenge", fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = onSimulateGuess,
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = DarkBackground,
                                contentColor = HighContrastCyan
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Simulate Match", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

        } else {
            // Permission Banner
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Camera Permission Required",
                    style = MaterialTheme.typography.headlineMedium,
                    color = HighContrastYellow,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "To test your GSL hand gestures in real time, camera permission is needed.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() },
                    colors = ButtonDefaults.buttonColors(containerColor = HighContrastYellow, contentColor = DarkBackground)
                ) {
                    Text("Grant Camera Permission", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
