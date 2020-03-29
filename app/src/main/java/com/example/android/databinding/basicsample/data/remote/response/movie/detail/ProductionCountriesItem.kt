package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

 data class ProductionCountriesItem(

        @SerializedName("iso_3166_1")
        var iso31661: String? = null,
        @SerializedName("name")
        var name: String? = null
)