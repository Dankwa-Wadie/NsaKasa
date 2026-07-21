package com.nsakasa.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.nsakasa.core.camera.HandLandmarkResult
import com.nsakasa.ui.theme.ConnectionLineColor
import com.nsakasa.ui.theme.JointPointColor
import kotlin.math.max

/**
 * 21 Hand Connections according to MediaPipe Hand Landmarker model topology.
 */
private val HAND_CONNECTIONS = listOf(
    // Thumb
    Pair(0, 1), Pair(1, 2), Pair(2, 3), Pair(3, 4),
    // Index finger
    Pair(0, 5), Pair(5, 6), Pair(6, 7), Pair(7, 8),
    // Middle finger
    Pair(9, 10), Pair(10, 11), Pair(11, 12),
    // Ring finger
    Pair(13, 14), Pair(14, 15), Pair(15, 16),
    // Pinky finger
    Pair(0, 17), Pair(17, 18), Pair(18, 19), Pair(19, 20),
    // Palm / Knuckle connections
    Pair(5, 9), Pair(9, 13), Pair(13, 17)
)

@Composable
fun LandmarkOverlay(
    landmarkResult: HandLandmarkResult?,
    isFrontCamera: Boolean = true,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        if (landmarkResult == null || landmarkResult.hands.isEmpty()) return@Canvas

        val canvasWidth = size.width
        val canvasHeight = size.height

        val imgWidth = landmarkResult.inputWidth.toFloat().takeIf { it > 0 } ?: canvasWidth
        val imgHeight = landmarkResult.inputHeight.toFloat().takeIf { it > 0 } ?: canvasHeight

        // Precise FILL_CENTER scaling transformation (matching CameraX PreviewView FILL_CENTER)
        val scale = max(canvasWidth / imgWidth, canvasHeight / imgHeight)
        val scaledWidth = imgWidth * scale
        val scaledHeight = imgHeight * scale
        val offsetX = (canvasWidth - scaledWidth) / 2f
        val offsetY = (canvasHeight - scaledHeight) / 2f

        for (hand in landmarkResult.hands) {
            val points = hand.points
            if (points.size < 21) continue

            // Compute pixel offsets with aspect-ratio cropping and front-camera mirroring
            val pixelPoints = points.map { pt ->
                val normX = if (isFrontCamera) 1.0f - pt.x else pt.x
                Offset(
                    x = normX * scaledWidth + offsetX,
                    y = pt.y * scaledHeight + offsetY
                )
            }

            // Draw connection lines
            for (connection in HAND_CONNECTIONS) {
                val startPx = pixelPoints[connection.first]
                val endPx = pixelPoints[connection.second]

                drawLine(
                    color = ConnectionLineColor,
                    start = startPx,
                    end = endPx,
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            // Draw joint landmark dots
            for (jointPx in pixelPoints) {
                drawCircle(
                    color = JointPointColor,
                    radius = 7.dp.toPx(),
                    center = jointPx
                )
            }
        }
    }
}
