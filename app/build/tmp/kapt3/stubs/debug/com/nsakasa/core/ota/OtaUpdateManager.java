package com.nsakasa.core.ota;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\tH\u0002J\u0016\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082D\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u001d"}, d2 = {"Lcom/nsakasa/core/ota/OtaUpdateManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_updateState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/nsakasa/core/ota/OtaUpdateState;", "githubReleaseUrl", "", "updateState", "Lkotlinx/coroutines/flow/StateFlow;", "getUpdateState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkForUpdates", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadApk", "downloadUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAppVersionName", "isNewerVersion", "", "current", "latest", "triggerApkInstallation", "apkFile", "Ljava/io/File;", "Companion", "app_debug"})
public final class OtaUpdateManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.nsakasa.core.ota.OtaUpdateState> _updateState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ota.OtaUpdateState> updateState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String githubReleaseUrl = "https://api.github.com/repos/Dankwa-Wadie/NsaKasa/releases/latest";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "OtaUpdateManager";
    @org.jetbrains.annotations.NotNull()
    public static final com.nsakasa.core.ota.OtaUpdateManager.Companion Companion = null;
    
    public OtaUpdateManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.nsakasa.core.ota.OtaUpdateState> getUpdateState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkForUpdates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object downloadApk(@org.jetbrains.annotations.NotNull()
    java.lang.String downloadUrl, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void triggerApkInstallation(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File apkFile) {
    }
    
    private final java.lang.String getAppVersionName(android.content.Context context) {
        return null;
    }
    
    private final boolean isNewerVersion(java.lang.String current, java.lang.String latest) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/nsakasa/core/ota/OtaUpdateManager$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}