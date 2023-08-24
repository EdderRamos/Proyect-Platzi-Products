package com.webcontrol.platzi.di

import android.content.Context
import com.webcontrol.platzi.R
import com.webcontrol.platzi.data.network.MainClient
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object ServerModule {

    @Singleton
    @Provides
    fun providePlatzi(@ApplicationContext context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.url_platzi))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginApiClient(retrofit: Retrofit): MainClient {
        return retrofit.create(MainClient::class.java)
    }
}