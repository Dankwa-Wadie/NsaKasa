package com.nsakasa.core.ota

import java.io.File

sealed class OtaUpdateState {
    object Idle : OtaUpdateState()
    object Checking : OtaUpdateState()
    data class UpdateAvailable(
        val versionName: String,
        val releaseNotes: String,
        val apkDownloadUrl: String
    ) : OtaUpdateState()
    object NoUpdateAvailable : OtaUpdateState()
    data class Downloading(val progress: Float) : OtaUpdateState()
    data class ReadyToInstall(val apkFile: File) : OtaUpdateState()
    data class Error(val message: String) : OtaUpdateState()
}
