package com.example.f1_project.data.models

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String): List<String> {
        return value.split(",")
    }
}
