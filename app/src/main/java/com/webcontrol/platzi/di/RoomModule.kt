package com.webcontrol.platzi.di

import android.content.Context
import androidx.room.Room
import com.webcontrol.platzi.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val PLATZI_DATABASE_NAME = "platzi_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, PLATZI_DATABASE_NAME).build()
    @Singleton
    @Provides
    fun getProductDao(db: AppDatabase ) = db.getProductDao()
}