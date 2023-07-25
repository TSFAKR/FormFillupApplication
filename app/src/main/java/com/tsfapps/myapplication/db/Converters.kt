package com.tsfapps.myapplication.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tsfapps.myapplication.db.entity.SendData
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    @TypeConverter
    fun fromCheckBoxList(value : MutableList<String>) = Json.encodeToString(value)

    private val gson = Gson()
    private val type = object : TypeToken<List<SendData>>() {}.type

    @TypeConverter
    fun stringToNestedData(json: String?): List<SendData> {
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nestedDataToString(nestedData: List<SendData>): String {
        return gson.toJson(nestedData, type)
    }

//    @TypeConverter
//    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}