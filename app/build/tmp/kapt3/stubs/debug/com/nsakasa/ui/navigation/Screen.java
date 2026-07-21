package com.nsakasa.ui.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0006\u000f\u0010\u0011\u0012\u0013\u0014B\'\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n\u0082\u0001\u0006\u0015\u0016\u0017\u0018\u0019\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen;", "", "route", "", "title", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "accessibilityLabel", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;)V", "getAccessibilityLabel", "()Ljava/lang/String;", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getRoute", "getTitle", "CameraTranslate", "ConversationLog", "GodotAvatar", "GslLearn", "Settings", "SpeechTranslate", "Lcom/nsakasa/ui/navigation/Screen$CameraTranslate;", "Lcom/nsakasa/ui/navigation/Screen$ConversationLog;", "Lcom/nsakasa/ui/navigation/Screen$GodotAvatar;", "Lcom/nsakasa/ui/navigation/Screen$GslLearn;", "Lcom/nsakasa/ui/navigation/Screen$Settings;", "Lcom/nsakasa/ui/navigation/Screen$SpeechTranslate;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.ui.graphics.vector.ImageVector icon = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String accessibilityLabel = null;
    
    private Screen(java.lang.String route, java.lang.String title, androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String accessibilityLabel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.compose.ui.graphics.vector.ImageVector getIcon() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccessibilityLabel() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$CameraTranslate;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class CameraTranslate extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.CameraTranslate INSTANCE = null;
        
        private CameraTranslate() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$ConversationLog;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class ConversationLog extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.ConversationLog INSTANCE = null;
        
        private ConversationLog() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$GodotAvatar;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class GodotAvatar extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.GodotAvatar INSTANCE = null;
        
        private GodotAvatar() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$GslLearn;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class GslLearn extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.GslLearn INSTANCE = null;
        
        private GslLearn() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$Settings;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class Settings extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.Settings INSTANCE = null;
        
        private Settings() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/nsakasa/ui/navigation/Screen$SpeechTranslate;", "Lcom/nsakasa/ui/navigation/Screen;", "()V", "app_debug"})
    public static final class SpeechTranslate extends com.nsakasa.ui.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.nsakasa.ui.navigation.Screen.SpeechTranslate INSTANCE = null;
        
        private SpeechTranslate() {
        }
    }
}