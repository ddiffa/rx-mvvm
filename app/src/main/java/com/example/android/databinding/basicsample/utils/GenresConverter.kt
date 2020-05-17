package com.example.android.databinding.basicsample.utils

import androidx.room.TypeConverter
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.GenresItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class GenresConverter {

    companion object {
        @JvmStatic
        @TypeConverter
        fun ListToJson(replyMessages: List<GenresItem?>?): String? {
            if (replyMessages == null) return null
            val type = object : TypeToken<List<GenresItem?>?>() {}.type
            val json: String = Gson().toJson(replyMessages, type)
            return if (replyMessages.isEmpty()) null else json
        }

        @JvmStatic
        @TypeConverter
        fun JsonToList(json: String?): List<GenresItem?>? {
            val gson = Gson()
            val type = object : TypeToken<List<GenresItem?>?>() {}.type
            return gson.fromJson(json, type)
        }
    }
}