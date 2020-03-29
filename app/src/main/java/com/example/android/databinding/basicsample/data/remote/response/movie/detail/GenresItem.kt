package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GenresItem(

        @ColumnInfo(name = "name_genre")
        @SerializedName("name")
        var name: String? = "",

        @ColumnInfo(name = "id_genre")
        @SerializedName("id")
        var id: Long? = 0
)