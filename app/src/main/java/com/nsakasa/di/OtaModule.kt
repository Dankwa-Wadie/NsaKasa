package com.nsakasa.di

import android.content.Context
import com.nsakasa.core.ota.OtaUpdateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OtaModule {

    @Provides
    @Singleton
    fun provideOtaUpdateManager(
        @ApplicationContext context: Context
    ): OtaUpdateManager {
        return OtaUpdateManager(context)
    }
}
