package com.webcontrol.platzi.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    @TypeConverter
    fun fromStringList(images: List<String>): String {
        return Gson().toJson(images)
    }

    @TypeConverter
    fun toStringList(imagesString: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(imagesString, listType)
    }
}