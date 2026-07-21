package com.nsakasa.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraFront
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nsakasa.features.cameratranslate.CameraTranslateScreen
import com.nsakasa.features.conversationlog.ConversationLogScreen
import com.nsakasa.features.speechtranslate.SpeechTranslateScreen
import com.nsakasa.features.settings.SettingsScreen
import com.nsakasa.ui.theme.DarkBackground
import com.nsakasa.ui.theme.DarkSurface
import com.nsakasa.ui.theme.HighContrastCyan
import com.nsakasa.ui.theme.HighContrastYellow

sealed class Screen(val route: String, val title: String, val icon: ImageVector, val accessibilityLabel: String) {
    object CameraTranslate : Screen("camera_translate", "Gesture", Icons.Default.CameraFront, "Sign language camera translation tab")
    object SpeechTranslate : Screen("speech_translate", "Speech", Icons.Default.Mic, "Speech to text reverse translation tab")
    object ConversationLog : Screen("conversation_log", "Log", Icons.Default.History, "Saved conversation history log tab")
    object Settings : Screen("settings", "Settings", Icons.Default.Settings, "Application settings tab")
}

@Composable
fun NsaKasaNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screens = listOf(
        Screen.CameraTranslate,
        Screen.SpeechTranslate,
        Screen.ConversationLog,
        Screen.Settings
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = DarkSurface,
                modifier = Modifier.height(72.dp)
            ) {
                screens.forEach { screen ->
                    val selected = currentRoute == screen.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = null,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                fontSize = 14.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = HighContrastYellow,
                            selectedTextColor = HighContrastYellow,
                            unselectedIconColor = HighContrastCyan,
                            unselectedTextColor = HighContrastCyan,
                            indicatorColor = DarkBackground
                        ),
                        modifier = Modifier.semantics {
                            contentDescription = screen.accessibilityLabel
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.CameraTranslate.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.CameraTranslate.route) {
                CameraTranslateScreen()
            }
            composable(Screen.SpeechTranslate.route) {
                SpeechTranslateScreen()
            }
            composable(Screen.ConversationLog.route) {
                ConversationLogScreen()
            }
            composable(Screen.Settings.route) {
                SettingsScreen()
            }
        }
    }
}
