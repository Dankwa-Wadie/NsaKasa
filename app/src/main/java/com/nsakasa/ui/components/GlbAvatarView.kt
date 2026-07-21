package com.nsakasa.ui.components

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Native WebGL 3D Avatar Visualizer rendering the actual 3D character mesh model
 * (avatar.glb) directly in Jetpack Compose using WebGL/Three.js.
 */
@Composable
fun GlbAvatarView(
    isZoomedIn: Boolean,
    gestureName: String,
    modifier: Modifier = Modifier
) {
    var webViewInstance: WebView? = null

    LaunchedEffect(isZoomedIn) {
        webViewInstance?.evaluateJavascript("zoomToHands($isZoomedIn);", null)
    }

    LaunchedEffect(gestureName) {
        webViewInstance?.evaluateJavascript("setGesture('$gestureName');", null)
        if (gestureName == "AKWAABA" || gestureName == "WAVE") {
            webViewInstance?.evaluateJavascript("waveHand();", null)
        }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = true
                    allowContentAccess = true
                    allowFileAccessFromFileURLs = true
                    allowUniversalAccessFromFileURLs = true
                    cacheMode = WebSettings.LOAD_NO_CACHE
                }
                loadUrl("file:///android_asset/3d_avatar/viewer.html")
                webViewInstance = this
            }
        },
        update = { webView ->
            webViewInstance = webView
            webView.evaluateJavascript("zoomToHands($isZoomedIn);", null)
        }
    )
}
