package com.anil.groceries.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converts {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(string: String?): List<String>? {
        return gson.fromJson(string, object : TypeToken<List<String?>?>() {}.type)
    }

    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return gson.toJson(list)
    }
}