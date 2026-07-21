package com.nsakasa.features.cameratranslate

import android.Manifest
import android.content.Context
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.nsakasa.core.camera.HandTrackingAnalyzer
import com.nsakasa.ui.components.LandmarkOverlay
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.HighContrastYellow

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraTranslateScreen(
    viewModel: CameraTranslateViewModel = hiltViewModel()
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val landmarkResult by viewModel.landmarkResult.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(cameraPermissionState.status.isGranted) {
        viewModel.setPermissionGranted(cameraPermissionState.status.isGranted)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        if (cameraPermissionState.status.isGranted) {
            val analyzer = remember { HandTrackingAnalyzer(context) }

            LaunchedEffect(analyzer) {
                analyzer.resultFlow.collect { result ->
                    viewModel.updateLandmarkResult(result)
                }
            }

            DisposableEffect(Unit) {
                onDispose {
                    analyzer.close()
                }
            }

            // CameraX Live Preview View
            AndroidView(
                factory = { ctx ->
                    val previewView = PreviewView(ctx).apply {
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                    }
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = Preview.Builder().build().also {
                            it.setSurfaceProvider(previewView.surfaceProvider)
                        }

                        val imageAnalysis = ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                            .also {
                                it.setAnalyzer(
                                    ContextCompat.getMainExecutor(ctx),
                                    analyzer
                                )
                            }

                        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalysis
                            )
                        } catch (e: Exception) {
                            Log.e("CameraTranslateScreen", "Use case binding failed", e)
                        }
                    }, ContextCompat.getMainExecutor(ctx))

                    previewView
                },
                modifier = Modifier.fillMaxSize()
            )

            // Real-time Hand Landmark Overlay
            LandmarkOverlay(
                landmarkResult = landmarkResult,
                modifier = Modifier.fillMaxSize()
            )

            // Status Banner / Top Header for visual confirmation & accessibility
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .background(DarkBackground.copy(alpha = 0.85f))
                    .padding(16.dp)
            ) {
                val detectedCount = landmarkResult?.hands?.size ?: 0
                Text(
                    text = if (detectedCount > 0) "Tracking: $detectedCount hand(s) detected" else "Nsa Kasa: Place hands in view",
                    style = MaterialTheme.typography.titleLarge,
                    color = HighContrastYellow,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = "Hand tracking status indicator"
                        }
                )
            }
        } else {
            // Permission Request UI with accessibility compliant touch target & text
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Text(
                    text = "Camera Permission Required",
                    style = MaterialTheme.typography.headlineMedium,
                    color = HighContrastYellow,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Nsa Kasa needs camera access to detect Ghanaian Sign Language hand gestures in real time.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { cameraPermissionState.launchPermissionRequest() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 200.dp, height = 56.dp)
                        .semantics {
                            contentDescription = "Grant camera permission button"
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
