package com.nsakasa.di;

import android.content.Context;
import com.nsakasa.core.ml.GestureClassifierInterface;
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
public final class MlModule_ProvideGestureClassifierFactory implements Factory<GestureClassifierInterface> {
  private final Provider<Context> contextProvider;

  public MlModule_ProvideGestureClassifierFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public GestureClassifierInterface get() {
    return provideGestureClassifier(contextProvider.get());
  }

  public static MlModule_ProvideGestureClassifierFactory create(Provider<Context> contextProvider) {
    return new MlModule_ProvideGestureClassifierFactory(contextProvider);
  }

  public static GestureClassifierInterface provideGestureClassifier(Context context) {
    return Preconditions.checkNotNullFromProvides(MlModule.INSTANCE.provideGestureClassifier(context));
  }
}
