package com.nsakasa.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val AccessibleDarkColorScheme = darkColorScheme(
    primary = HighContrastYellow,
    onPrimary = DarkBackground,
    secondary = HighContrastGreen,
    onSecondary = DarkBackground,
    tertiary = HighContrastCyan,
    onTertiary = DarkBackground,
    background = DarkBackground,
    onBackground = HighContrastWhite,
    surface = DarkSurface,
    onSurface = HighContrastWhite
)

@Composable
fun NsaKasaTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = AccessibleDarkColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
