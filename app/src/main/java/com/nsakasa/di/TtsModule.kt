package com.nsakasa.di

import android.content.Context
import com.nsakasa.core.tts.TtsDebouncer
import com.nsakasa.core.tts.TtsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TtsModule {

    @Provides
    @Singleton
    fun provideTtsManager(
        @ApplicationContext context: Context
    ): TtsManager {
        return TtsManager(context)
    }

    @Provides
    @Singleton
    fun provideTtsDebouncer(
        ttsManager: TtsManager
    ): TtsDebouncer {
        return TtsDebouncer(ttsManager)
    }
}
