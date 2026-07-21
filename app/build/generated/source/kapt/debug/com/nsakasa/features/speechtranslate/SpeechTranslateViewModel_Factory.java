package com.nsakasa.features.speechtranslate;

import com.nsakasa.core.data.ConversationDao;
import com.nsakasa.core.stt.SttManager;
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
public final class SpeechTranslateViewModel_Factory implements Factory<SpeechTranslateViewModel> {
  private final Provider<SttManager> sttManagerProvider;

  private final Provider<ConversationDao> conversationDaoProvider;

  public SpeechTranslateViewModel_Factory(Provider<SttManager> sttManagerProvider,
      Provider<ConversationDao> conversationDaoProvider) {
    this.sttManagerProvider = sttManagerProvider;
    this.conversationDaoProvider = conversationDaoProvider;
  }

  @Override
  public SpeechTranslateViewModel get() {
    return newInstance(sttManagerProvider.get(), conversationDaoProvider.get());
  }

  public static SpeechTranslateViewModel_Factory create(Provider<SttManager> sttManagerProvider,
      Provider<ConversationDao> conversationDaoProvider) {
    return new SpeechTranslateViewModel_Factory(sttManagerProvider, conversationDaoProvider);
  }

  public static SpeechTranslateViewModel newInstance(SttManager sttManager,
      ConversationDao conversationDao) {
    return new SpeechTranslateViewModel(sttManager, conversationDao);
  }
}
