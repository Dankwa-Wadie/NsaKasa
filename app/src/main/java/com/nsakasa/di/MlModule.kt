package com.nsakasa.di

import android.content.Context
import com.nsakasa.core.ml.GestureClassifier
import com.nsakasa.core.ml.GestureClassifierInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MlModule {

    @Provides
    @Singleton
    fun provideGestureClassifier(
        @ApplicationContext context: Context
    ): GestureClassifierInterface {
        return GestureClassifier(context)
    }
}
