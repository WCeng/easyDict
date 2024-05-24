package com.wceng.easydict

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun listToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToList(s: String): List<String> {
        val typeToken = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(s, typeToken)
    }

    @TypeConverter
    fun listMapToString(listMap: List<Map<String, List<String>>>?): String {
        if(listMap == null) return ""
        return gson.toJson(listMap)
    }

    @TypeConverter
    fun stringToListMap(s: String): List<Map<String, List<String>>> {
        val typeToken = object : TypeToken<List<Map<String, List<String>>>>() {}.type
        return gson.fromJson(s, typeToken)
    }
}