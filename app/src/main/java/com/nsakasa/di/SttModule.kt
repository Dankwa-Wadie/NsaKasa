package com.nsakasa.di

import android.content.Context
import com.nsakasa.core.stt.SttManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SttModule {

    @Provides
    @Singleton
    fun provideSttManager(
        @ApplicationContext context: Context
    ): SttManager {
        return SttManager(context)
    }
}
