package com.tsfapps.myapplication.db

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    @TypeConverter
    fun fromCheckBoxList(value : MutableList<String>) = Json.encodeToString(value)


//    @TypeConverter
//    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}