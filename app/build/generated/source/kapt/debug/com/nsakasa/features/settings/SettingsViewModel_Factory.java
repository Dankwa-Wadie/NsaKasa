package com.nsakasa.features.settings;

import com.nsakasa.core.ota.OtaUpdateManager;
import com.nsakasa.core.tts.TtsDebouncer;
import com.nsakasa.core.tts.TtsManager;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<TtsManager> ttsManagerProvider;

  private final Provider<TtsDebouncer> ttsDebouncerProvider;

  private final Provider<OtaUpdateManager> otaUpdateManagerProvider;

  public SettingsViewModel_Factory(Provider<TtsManager> ttsManagerProvider,
      Provider<TtsDebouncer> ttsDebouncerProvider,
      Provider<OtaUpdateManager> otaUpdateManagerProvider) {
    this.ttsManagerProvider = ttsManagerProvider;
    this.ttsDebouncerProvider = ttsDebouncerProvider;
    this.otaUpdateManagerProvider = otaUpdateManagerProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(ttsManagerProvider.get(), ttsDebouncerProvider.get(), otaUpdateManagerProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<TtsManager> ttsManagerProvider,
      Provider<TtsDebouncer> ttsDebouncerProvider,
      Provider<OtaUpdateManager> otaUpdateManagerProvider) {
    return new SettingsViewModel_Factory(ttsManagerProvider, ttsDebouncerProvider, otaUpdateManagerProvider);
  }

  public static SettingsViewModel newInstance(TtsManager ttsManager, TtsDebouncer ttsDebouncer,
      OtaUpdateManager otaUpdateManager) {
    return new SettingsViewModel(ttsManager, ttsDebouncer, otaUpdateManager);
  }
}
