package com.nsakasa.di;

import com.nsakasa.core.tts.TtsDebouncer;
import com.nsakasa.core.tts.TtsManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TtsModule_ProvideTtsDebouncerFactory implements Factory<TtsDebouncer> {
  private final Provider<TtsManager> ttsManagerProvider;

  public TtsModule_ProvideTtsDebouncerFactory(Provider<TtsManager> ttsManagerProvider) {
    this.ttsManagerProvider = ttsManagerProvider;
  }

  @Override
  public TtsDebouncer get() {
    return provideTtsDebouncer(ttsManagerProvider.get());
  }

  public static TtsModule_ProvideTtsDebouncerFactory create(
      Provider<TtsManager> ttsManagerProvider) {
    return new TtsModule_ProvideTtsDebouncerFactory(ttsManagerProvider);
  }

  public static TtsDebouncer provideTtsDebouncer(TtsManager ttsManager) {
    return Preconditions.checkNotNullFromProvides(TtsModule.INSTANCE.provideTtsDebouncer(ttsManager));
  }
}
