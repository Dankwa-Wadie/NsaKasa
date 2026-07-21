package com.nsakasa.features.cameratranslate;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
  @Override
  public CameraTranslateViewModel get() {
    return newInstance();
  }

  public static CameraTranslateViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CameraTranslateViewModel newInstance() {
    return new CameraTranslateViewModel();
  }

  private static final class InstanceHolder {
    private static final CameraTranslateViewModel_Factory INSTANCE = new CameraTranslateViewModel_Factory();
  }
}
