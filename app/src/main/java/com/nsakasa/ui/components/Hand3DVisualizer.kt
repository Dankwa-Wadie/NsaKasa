package com.nsakasa.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nsakasa.core.camera.LandmarkPoint
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastGreen
import com.nsakasa.ui.theme.HighContrastYellow
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

/**
 * Interactive 3D Hand Skeleton & Mesh Visualizer.
 * Renders 21 3D joint landmarks in Jetpack Compose Canvas with perspective projection.
 * Supports 360-degree touch rotation around Yaw and Pitch.
 */
@Composable
fun Hand3DVisualizer(
    landmarks: List<LandmarkPoint>,
    modifier: Modifier = Modifier,
    autoRotate: Boolean = false
) {
    var rotX by remember { mutableFloatStateOf(15f) }
    var rotY by remember { mutableFloatStateOf(0f) }
    var isAutoRotating by remember { mutableStateOf(autoRotate) }

    // Auto rotation loop if enabled
    LaunchedEffect(isAutoRotating) {
        while (isAutoRotating) {
            rotY = (rotY + 1.5f) % 360f
            delay(16) // ~60fps
        }
    }

    // Connections between MediaPipe 21 hand landmarks
    val boneConnections = listOf(
        // Palm / Base
        0 to 1, 1 to 2, 2 to 3, 3 to 4,    // Thumb
        0 to 5, 5 to 6, 6 to 7, 7 to 8,    // Index
        9 to 10, 10 to 11, 11 to 12,       // Middle
        13 to 14, 14 to 15, 15 to 16,      // Ring
        17 to 18, 18 to 19, 19 to 20,      // Pinky
        // Palm horizontal connections
        0 to 17, 5 to 9, 9 to 13, 13 to 17
    )

    val fingerTips = setOf(4, 8, 12, 16, 20)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(DarkSurface)
            .semantics {
                contentDescription = "Interactive 3D hand model visualization. Drag to rotate 360 degrees."
            }
    ) {
        // 3D Canvas Renderer
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, _, _ ->
                        isAutoRotating = false
                        rotY += pan.x * 0.5f
                        rotX = (rotX - pan.y * 0.5f).coerceIn(-85f, 85f)
                    }
                }
        ) {
            if (landmarks.isEmpty()) return@Canvas

            val canvasWidth = size.width
            val canvasHeight = size.height
            val centerScreen = Offset(canvasWidth / 2f, canvasHeight / 2f)

            // Compute center of 3D points
            var sumX = 0f
            var sumY = 0f
            var sumZ = 0f
            landmarks.forEach { pt ->
                sumX += pt.x
                sumY += pt.y
                sumZ += pt.z
            }
            val avgX = sumX / landmarks.size
            val avgY = sumY / landmarks.size
            val avgZ = sumZ / landmarks.size

            val radX = Math.toRadians(rotX.toDouble())
            val radY = Math.toRadians(rotY.toDouble())
            val cosX = cos(radX).toFloat()
            val sinX = sin(radX).toFloat()
            val cosY = cos(radY).toFloat()
            val sinY = sin(radY).toFloat()

            val baseScale = minOf(canvasWidth, canvasHeight) * 0.75f
            val cameraDist = 2.5f

            // Project 3D points to 2D
            val projectedPoints = landmarks.map { pt ->
                val cx = (pt.x - avgX)
                val cy = (pt.y - avgY)
                val cz = (pt.z - avgZ)

                // Rotate Y (Yaw)
                val x1 = cx * cosY + cz * sinY
                val z1 = -cx * sinY + cz * cosY

                // Rotate X (Pitch)
                val y2 = cy * cosX - z1 * sinX
                val z2 = cy * sinX + z1 * cosX

                // Perspective projection calculation
                val perspective = cameraDist / (cameraDist + z2)
                val px = centerScreen.x + x1 * baseScale * perspective
                val py = centerScreen.y + y2 * baseScale * perspective

                Triple(Offset(px, py), perspective, z2)
            }

            // Draw bone connection lines in 3D
            boneConnections.forEach { (fromIdx, toIdx) ->
                if (fromIdx < projectedPoints.size && toIdx < projectedPoints.size) {
                    val (p1, pers1, _) = projectedPoints[fromIdx]
                    val (p2, pers2, _) = projectedPoints[toIdx]

                    val avgPers = (pers1 + pers2) / 2f
                    val strokeWidth = (4.dp.toPx() * avgPers).coerceIn(2f, 10f)

                    drawLine(
                        color = HighContrastCyan,
                        start = p1,
                        end = p2,
                        strokeWidth = strokeWidth,
                        cap = StrokeCap.Round
                    )
                }
            }

            // Draw 3D joint nodes
            projectedPoints.forEachIndexed { idx, (pos, pers, _) ->
                val isTip = fingerTips.contains(idx)
                val radius = if (isTip) (7.dp.toPx() * pers) else (5.dp.toPx() * pers)
                val color = when {
                    idx == 0 -> HighContrastYellow // Wrist
                    isTip -> HighContrastGreen    // Fingertips
                    else -> HighContrastYellow   // Finger joints
                }

                drawCircle(
                    color = color,
                    radius = radius.coerceIn(3f, 16f),
                    center = pos
                )
            }
        }

        // Overlay 3D Navigation & Rotation Controls
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(DarkBackground.copy(alpha = 0.85f))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ScreenRotation,
                    contentDescription = null,
                    tint = HighContrastYellow,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Drag 360° to rotate 3D hand",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = HighContrastYellow,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    onClick = { isAutoRotating = !isAutoRotating },
                    shape = RoundedCornerShape(12.dp),
                    color = if (isAutoRotating) HighContrastGreen else DarkSurface,
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    Text(
                        text = if (isAutoRotating) "Auto: ON" else "Auto: OFF",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isAutoRotating) DarkBackground else HighContrastCyan,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                IconButton(
                    onClick = {
                        rotX = 15f
                        rotY = 0f
                        isAutoRotating = false
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Reset 3D view angle",
                        tint = HighContrastCyan,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
