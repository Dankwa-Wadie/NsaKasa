package com.nsakasa.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.nsakasa.core.camera.LandmarkPoint
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastYellow
import kotlin.math.cos
import kotlin.math.sin

/**
 * 3D Avatar Character Visualizer component rendering the 3D character model
 * (head, torso, arms, hands) with idle breathing, waving, and camera zoom modes.
 */
@Composable
fun Avatar3DVisualizer(
    landmarks: List<LandmarkPoint>,
    gestureName: String,
    isZoomedIn: Boolean,
    modifier: Modifier = Modifier
) {
    var rotationY by remember { mutableStateOf(0f) }
    var rotationX by remember { mutableStateOf(0f) }

    // Infinite transition for idle breathing animation
    val infiniteTransition = rememberInfiniteTransition(label = "breathing")
    val breathScale by infiniteTransition.animateFloat(
        initialValue = 0.98f,
        targetValue = 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "breathScale"
    )

    // Wave animation offset
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "waveOffset"
    )

    Box(
        modifier = modifier
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF1E293B),
                        Color(0xFF0F172A)
                    )
                )
            )
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    rotationY += pan.x * 0.4f
                    rotationX = (rotationX + pan.y * 0.4f).coerceIn(-30f, 30f)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val zoomFactor = if (isZoomedIn) 2.2f else 1.0f
            val offsetY = if (isZoomedIn) height * 0.25f else 0f

            val angleYRad = Math.toRadians(rotationY.toDouble())

            fun project(x: Float, y: Float, z: Float): Offset {
                val rotX = (x * cos(angleYRad) - z * sin(angleYRad)).toFloat()
                val rotZ = (x * sin(angleYRad) + z * cos(angleYRad)).toFloat()
                val scale = (300f / (300f + rotZ)) * zoomFactor
                val screenX = (width / 2f) + (rotX * scale)
                val screenY = (height / 2f) + (y * scale) + offsetY
                return Offset(screenX, screenY)
            }

            // Draw 3D Character Model Rig (Head, Neck, Torso, Shoulders, Arms, Hands)
            val headCenter = project(0f, -140f * breathScale, 0f)
            val neck = project(0f, -90f, 0f)
            val leftShoulder = project(-70f, -70f, 0f)
            val rightShoulder = project(70f, -70f, 0f)
            val waist = project(0f, 70f, 0f)

            // Draw Torso
            val torsoPath = Path().apply {
                moveTo(leftShoulder.x, leftShoulder.y)
                lineTo(rightShoulder.x, rightShoulder.y)
                lineTo(waist.x + 30f, waist.y)
                lineTo(waist.x - 30f, waist.y)
                close()
            }
            drawPath(path = torsoPath, color = HighContrastCyan.copy(alpha = 0.35f))
            drawPath(path = torsoPath, color = HighContrastCyan, style = Stroke(width = 3f))

            // Draw Head
            drawCircle(color = HighContrastYellow, radius = 32f * zoomFactor, center = headCenter)
            drawLine(color = HighContrastYellow, start = headCenter, end = neck, strokeWidth = 5f)

            // Draw Waving / Gesturing Arms & Hands
            val waveAngle = if (gestureName == "AKWAABA" || gestureName == "IDLE") waveOffset else 0f
            val rightElbow = project(110f, -30f + waveAngle, 20f)
            val rightHand = project(140f, -80f + waveAngle, 40f)

            val leftElbow = project(-110f, -20f, 20f)
            val leftHand = project(-140f, -50f, 40f)

            // Right Arm
            drawLine(color = HighContrastGreen, start = rightShoulder, end = rightElbow, strokeWidth = 6f)
            drawLine(color = HighContrastGreen, start = rightElbow, end = rightHand, strokeWidth = 5f)
            drawCircle(color = HighContrastYellow, radius = 14f * zoomFactor, center = rightHand)

            // Left Arm
            drawLine(color = HighContrastGreen, start = leftShoulder, end = leftElbow, strokeWidth = 6f)
            drawLine(color = HighContrastGreen, start = leftElbow, end = leftHand, strokeWidth = 5f)
            drawCircle(color = HighContrastYellow, radius = 14f * zoomFactor, center = leftHand)

            // Draw Detailed 3D Hand Landmarks on Avatar Hands when Zoomed In
            landmarks.forEach { pt ->
                val handX = (pt.x - 0.5f) * 160f
                val handY = (pt.y - 0.5f) * 160f
                val ptScreen = project(handX, handY, pt.z * 160f)
                drawCircle(color = HighContrastYellow, radius = 5f * zoomFactor, center = ptScreen)
            }
        }
    }
}
