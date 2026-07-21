package com.nsakasa.di;

import android.content.Context;
import com.nsakasa.core.tts.TtsManager;
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
public final class TtsModule_ProvideTtsManagerFactory implements Factory<TtsManager> {
  private final Provider<Context> contextProvider;

  public TtsModule_ProvideTtsManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TtsManager get() {
    return provideTtsManager(contextProvider.get());
  }

  public static TtsModule_ProvideTtsManagerFactory create(Provider<Context> contextProvider) {
    return new TtsModule_ProvideTtsManagerFactory(contextProvider);
  }

  public static TtsManager provideTtsManager(Context context) {
    return Preconditions.checkNotNullFromProvides(TtsModule.INSTANCE.provideTtsManager(context));
  }
}
