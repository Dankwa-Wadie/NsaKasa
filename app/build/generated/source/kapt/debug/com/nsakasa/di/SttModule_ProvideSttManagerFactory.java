package com.nsakasa.di;

import android.content.Context;
import com.nsakasa.core.stt.SttManager;
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
public final class SttModule_ProvideSttManagerFactory implements Factory<SttManager> {
  private final Provider<Context> contextProvider;

  public SttModule_ProvideSttManagerFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SttManager get() {
    return provideSttManager(contextProvider.get());
  }

  public static SttModule_ProvideSttManagerFactory create(Provider<Context> contextProvider) {
    return new SttModule_ProvideSttManagerFactory(contextProvider);
  }

  public static SttManager provideSttManager(Context context) {
    return Preconditions.checkNotNullFromProvides(SttModule.INSTANCE.provideSttManager(context));
  }
}
