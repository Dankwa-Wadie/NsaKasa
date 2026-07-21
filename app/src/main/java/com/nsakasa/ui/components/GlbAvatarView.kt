package com.nsakasa.ui.components

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }
    var isPageLoaded by remember { mutableStateOf(false) }

    // Evaluate JavaScript safely only after the page has finished loading
    LaunchedEffect(isPageLoaded, isZoomedIn) {
        if (isPageLoaded) {
            webViewInstance?.evaluateJavascript("if (window.zoomToHands) { window.zoomToHands($isZoomedIn); }", null)
        }
    }

    LaunchedEffect(isPageLoaded, gestureName) {
        if (isPageLoaded) {
            webViewInstance?.evaluateJavascript("if (window.setGesture) { window.setGesture('$gestureName'); }", null)
            if (gestureName == "AKWAABA" || gestureName == "WAVE") {
                webViewInstance?.evaluateJavascript("if (window.waveHand) { window.waveHand(); }", null)
            }
        }
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isPageLoaded = true
                    }
                }
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
            if (isPageLoaded) {
                webView.evaluateJavascript("if (window.zoomToHands) { window.zoomToHands($isZoomedIn); }", null)
            }
        }
    )
}
