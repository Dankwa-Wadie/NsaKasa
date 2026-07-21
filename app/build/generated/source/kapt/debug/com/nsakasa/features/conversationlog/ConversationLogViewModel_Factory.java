package com.nsakasa.features.conversationlog;

import com.nsakasa.core.data.ConversationDao;
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
public final class ConversationLogViewModel_Factory implements Factory<ConversationLogViewModel> {
  private final Provider<ConversationDao> conversationDaoProvider;

  public ConversationLogViewModel_Factory(Provider<ConversationDao> conversationDaoProvider) {
    this.conversationDaoProvider = conversationDaoProvider;
  }

  @Override
  public ConversationLogViewModel get() {
    return newInstance(conversationDaoProvider.get());
  }

  public static ConversationLogViewModel_Factory create(
      Provider<ConversationDao> conversationDaoProvider) {
    return new ConversationLogViewModel_Factory(conversationDaoProvider);
  }

  public static ConversationLogViewModel newInstance(ConversationDao conversationDao) {
    return new ConversationLogViewModel(conversationDao);
  }
}
