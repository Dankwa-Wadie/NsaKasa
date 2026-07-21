package com.nsakasa.core.ota

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class OtaUpdateManager(private val context: Context) {

    private val _updateState = MutableStateFlow<OtaUpdateState>(OtaUpdateState.Idle)
    val updateState: StateFlow<OtaUpdateState> = _updateState.asStateFlow()

    private val githubReleaseUrl = "https://api.github.com/repos/Dankwa-Wadie/NsaKasa/releases/latest"

    suspend fun checkForUpdates() = withContext(Dispatchers.IO) {
        _updateState.value = OtaUpdateState.Checking
        try {
            val url = URL(githubReleaseUrl)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "GET"
                setRequestProperty("Accept", "application/vnd.github+json")
                connectTimeout = 8000
                readTimeout = 8000
            }

            if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val jsonStr = connection.inputStream.bufferedReader().use { it.readText() }
                val jsonObj = JSONObject(jsonStr)

                val tagName = jsonObj.optString("tag_name", "v1.0.0")
                val releaseNotes = jsonObj.optString("body", "Bug fixes and performance improvements.")
                val assetsArray = jsonObj.optJSONArray("assets")

                var apkUrl: String? = null
                if (assetsArray != null) {
                    for (i in 0 until assetsArray.length()) {
                        val asset = assetsArray.getJSONObject(i)
                        val name = asset.optString("name", "")
                        if (name.endsWith(".apk", ignoreCase = true)) {
                            apkUrl = asset.optString("browser_download_url")
                            break
                        }
                    }
                }

                val currentVersion = getAppVersionName(context)
                val cleanTag = tagName.removePrefix("v")

                if (isNewerVersion(currentVersion, cleanTag) && !apkUrl.isNullOrEmpty()) {
                    _updateState.value = OtaUpdateState.UpdateAvailable(
                        versionName = tagName,
                        releaseNotes = releaseNotes,
                        apkDownloadUrl = apkUrl
                    )
                } else {
                    _updateState.value = OtaUpdateState.NoUpdateAvailable
                }
            } else {
                _updateState.value = OtaUpdateState.NoUpdateAvailable
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to check for GitHub release OTA update: ${e.message}", e)
            _updateState.value = OtaUpdateState.Error("Could not check updates: ${e.message}")
        }
    }

    suspend fun downloadApk(downloadUrl: String) = withContext(Dispatchers.IO) {
        _updateState.value = OtaUpdateState.Downloading(0.0f)
        try {
            val apkDir = File(context.cacheDir, "apks").apply { mkdirs() }
            val outputFile = File(apkDir, "NsaKasa-update.apk")
            if (outputFile.exists()) outputFile.delete()

            val url = URL(downloadUrl)
            val connection = (url.openConnection() as HttpURLConnection).apply {
                connectTimeout = 15000
                readTimeout = 15000
            }

            val totalSize = connection.contentLength
            var downloadedBytes = 0

            connection.inputStream.use { input ->
                FileOutputStream(outputFile).use { output ->
                    val buffer = ByteArray(8192)
                    var bytesRead: Int
                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        output.write(buffer, 0, bytesRead)
                        downloadedBytes += bytesRead
                        if (totalSize > 0) {
                            val progress = downloadedBytes.toFloat() / totalSize.toFloat()
                            _updateState.value = OtaUpdateState.Downloading(progress)
                        }
                    }
                }
            }

            _updateState.value = OtaUpdateState.ReadyToInstall(outputFile)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to download update APK: ${e.message}", e)
            _updateState.value = OtaUpdateState.Error("APK download failed: ${e.message}")
        }
    }

    fun triggerApkInstallation(context: Context, apkFile: File) {
        try {
            val apkUri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                apkFile
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to launch package installer: ${e.message}", e)
            _updateState.value = OtaUpdateState.Error("Installer launch failed: ${e.message}")
        }
    }

    private fun getAppVersionName(context: Context): String {
        return try {
            val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            pInfo.versionName ?: "1.0.0"
        } catch (e: Exception) {
            "1.0.0"
        }
    }

    private fun isNewerVersion(current: String, latest: String): Boolean {
        return try {
            val currentParts = current.split("-")[0].split(".").mapNotNull { it.toIntOrNull() }
            val latestParts = latest.split("-")[0].split(".").mapNotNull { it.toIntOrNull() }
            
            for (i in 0 until maxOf(currentParts.size, latestParts.size)) {
                val c = currentParts.getOrElse(i) { 0 }
                val l = latestParts.getOrElse(i) { 0 }
                if (l > c) return true
                if (l < c) return false
            }
            false
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        private const val TAG = "OtaUpdateManager"
    }
}
