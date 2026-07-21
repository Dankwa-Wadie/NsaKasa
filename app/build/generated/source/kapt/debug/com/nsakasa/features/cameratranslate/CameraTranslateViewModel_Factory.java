package com.nsakasa.features.cameratranslate;

import com.nsakasa.core.ml.GestureClassifierInterface;
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
public final class CameraTranslateViewModel_Factory implements Factory<CameraTranslateViewModel> {
  private final Provider<GestureClassifierInterface> gestureClassifierProvider;

  public CameraTranslateViewModel_Factory(
      Provider<GestureClassifierInterface> gestureClassifierProvider) {
    this.gestureClassifierProvider = gestureClassifierProvider;
  }

  @Override
  public CameraTranslateViewModel get() {
    return newInstance(gestureClassifierProvider.get());
  }

  public static CameraTranslateViewModel_Factory create(
      Provider<GestureClassifierInterface> gestureClassifierProvider) {
    return new CameraTranslateViewModel_Factory(gestureClassifierProvider);
  }

  public static CameraTranslateViewModel newInstance(GestureClassifierInterface gestureClassifier) {
    return new CameraTranslateViewModel(gestureClassifier);
  }
}
