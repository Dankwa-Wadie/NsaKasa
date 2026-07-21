package com.nsakasa.di;

import android.content.Context;
import com.nsakasa.core.ota.OtaUpdateManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class OtaModule_ProvideOtaUpdateManagerFactory implements Factory<OtaUpdateManager> {
  private final Provider<Context> contextProvider;

  public OtaModule_ProvideOtaUpdateManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public OtaUpdateManager get() {
    return provideOtaUpdateManager(contextProvider.get());
  }

  public static OtaModule_ProvideOtaUpdateManagerFactory create(Provider<Context> contextProvider) {
    return new OtaModule_ProvideOtaUpdateManagerFactory(contextProvider);
  }

  public static OtaUpdateManager provideOtaUpdateManager(Context context) {
    return Preconditions.checkNotNullFromProvides(OtaModule.INSTANCE.provideOtaUpdateManager(context));
  }
}
