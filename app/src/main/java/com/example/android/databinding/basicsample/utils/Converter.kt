package com.example.android.databinding.basicsample.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    companion object {
        @JvmStatic
        @TypeConverter
        fun fromStringToList(data: String): List<String> {
            val listType =
                    object : TypeToken<List<String?>?>() {}.type
            return Gson().fromJson(data, listType)
        }

        @JvmStatic
        @TypeConverter
        fun listToString(data: List<String>): String {
            return Gson().toJson(data)
        }
    }
}