package com.webcontrol.platzi.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.webcontrol.platzi.core.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }
    @Provides
    fun provideSharedPreferencesUtil(sharedPreferences: SharedPreferences): SharedPreferencesUtil {
        return SharedPreferencesUtil(sharedPreferences)
    }
}