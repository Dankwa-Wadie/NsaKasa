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
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        if (landmarkResult == null || landmarkResult.hands.isEmpty()) return@Canvas

        val canvasWidth = size.width
        val canvasHeight = size.height

        for (hand in landmarkResult.hands) {
            val points = hand.points
            if (points.size < 21) continue

            // Draw connection lines
            for (connection in HAND_CONNECTIONS) {
                val startPoint = points[connection.first]
                val endPoint = points[connection.second]

                val startPx = Offset(startPoint.x * canvasWidth, startPoint.y * canvasHeight)
                val endPx = Offset(endPoint.x * canvasWidth, endPoint.y * canvasHeight)

                drawLine(
                    color = ConnectionLineColor,
                    start = startPx,
                    end = endPx,
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            // Draw joint landmark dots
            for (point in points) {
                val jointPx = Offset(point.x * canvasWidth, point.y * canvasHeight)
                drawCircle(
                    color = JointPointColor,
                    radius = 7.dp.toPx(),
                    center = jointPx
                )
            }
        }
    }
}
