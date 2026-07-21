package com.nsakasa.features.cameratranslate;

import com.nsakasa.core.data.ConversationDao;
import com.nsakasa.core.ml.GestureClassifierInterface;
import com.nsakasa.core.tts.TtsDebouncer;
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

  private final Provider<TtsDebouncer> ttsDebouncerProvider;

  private final Provider<ConversationDao> conversationDaoProvider;

  public CameraTranslateViewModel_Factory(
      Provider<GestureClassifierInterface> gestureClassifierProvider,
      Provider<TtsDebouncer> ttsDebouncerProvider,
      Provider<ConversationDao> conversationDaoProvider) {
    this.gestureClassifierProvider = gestureClassifierProvider;
    this.ttsDebouncerProvider = ttsDebouncerProvider;
    this.conversationDaoProvider = conversationDaoProvider;
  }

  @Override
  public CameraTranslateViewModel get() {
    return newInstance(gestureClassifierProvider.get(), ttsDebouncerProvider.get(), conversationDaoProvider.get());
  }

  public static CameraTranslateViewModel_Factory create(
      Provider<GestureClassifierInterface> gestureClassifierProvider,
      Provider<TtsDebouncer> ttsDebouncerProvider,
      Provider<ConversationDao> conversationDaoProvider) {
    return new CameraTranslateViewModel_Factory(gestureClassifierProvider, ttsDebouncerProvider, conversationDaoProvider);
  }

  public static CameraTranslateViewModel newInstance(GestureClassifierInterface gestureClassifier,
      TtsDebouncer ttsDebouncer, ConversationDao conversationDao) {
    return new CameraTranslateViewModel(gestureClassifier, ttsDebouncer, conversationDao);
  }
}
