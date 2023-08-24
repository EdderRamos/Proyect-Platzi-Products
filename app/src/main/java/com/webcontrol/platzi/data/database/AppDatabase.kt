package com.webcontrol.platzi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.webcontrol.platzi.data.database.entities.ProductEntity
import com.webcontrol.platzi.data.database.dao.ProductDao
import com.webcontrol.platzi.data.database.entities.CategoryEntity
import com.webcontrol.platzi.data.database.converters.CategoryEntityConverter
import com.webcontrol.platzi.data.database.converters.StringListConverter

@Database(entities = [ProductEntity::class, CategoryEntity::class], version = 1)
@TypeConverters(CategoryEntityConverter::class, StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}
