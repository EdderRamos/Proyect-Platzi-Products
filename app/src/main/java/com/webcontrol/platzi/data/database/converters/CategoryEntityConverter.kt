package com.webcontrol.platzi.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.webcontrol.platzi.data.database.entities.CategoryEntity

class CategoryEntityConverter {
    @TypeConverter
    fun fromCategoryEntity(category: CategoryEntity): String {
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toCategoryEntity(categoryString: String): CategoryEntity {
        return Gson().fromJson(categoryString, CategoryEntity::class.java)
    }
}