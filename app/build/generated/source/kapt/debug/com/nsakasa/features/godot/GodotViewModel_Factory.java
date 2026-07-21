package com.nsakasa.features.godot;

import com.nsakasa.core.godot.GodotBridgePlugin;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class GodotViewModel_Factory implements Factory<GodotViewModel> {
  private final Provider<GodotBridgePlugin> godotBridgePluginProvider;

  public GodotViewModel_Factory(Provider<GodotBridgePlugin> godotBridgePluginProvider) {
    this.godotBridgePluginProvider = godotBridgePluginProvider;
  }

  @Override
  public GodotViewModel get() {
    return newInstance(godotBridgePluginProvider.get());
  }

  public static GodotViewModel_Factory create(
      Provider<GodotBridgePlugin> godotBridgePluginProvider) {
    return new GodotViewModel_Factory(godotBridgePluginProvider);
  }

  public static GodotViewModel newInstance(GodotBridgePlugin godotBridgePlugin) {
    return new GodotViewModel(godotBridgePlugin);
  }
}
