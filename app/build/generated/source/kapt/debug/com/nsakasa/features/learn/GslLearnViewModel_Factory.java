package com.nsakasa.features.learn;

import com.nsakasa.core.ml.GestureClassifierInterface;
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
public final class GslLearnViewModel_Factory implements Factory<GslLearnViewModel> {
  private final Provider<GestureClassifierInterface> gestureClassifierProvider;

  private final Provider<TtsManager> ttsManagerProvider;

  public GslLearnViewModel_Factory(Provider<GestureClassifierInterface> gestureClassifierProvider,
      Provider<TtsManager> ttsManagerProvider) {
    this.gestureClassifierProvider = gestureClassifierProvider;
    this.ttsManagerProvider = ttsManagerProvider;
  }

  @Override
  public GslLearnViewModel get() {
    return newInstance(gestureClassifierProvider.get(), ttsManagerProvider.get());
  }

  public static GslLearnViewModel_Factory create(
      Provider<GestureClassifierInterface> gestureClassifierProvider,
      Provider<TtsManager> ttsManagerProvider) {
    return new GslLearnViewModel_Factory(gestureClassifierProvider, ttsManagerProvider);
  }

  public static GslLearnViewModel newInstance(GestureClassifierInterface gestureClassifier,
      TtsManager ttsManager) {
    return new GslLearnViewModel(gestureClassifier, ttsManager);
  }
}
