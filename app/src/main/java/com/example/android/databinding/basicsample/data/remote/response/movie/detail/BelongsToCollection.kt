package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class BelongsToCollection(

        @SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("id")
        @PrimaryKey(autoGenerate = false)
        var id: Long? = null,

        @SerializedName("poster_path")
        var posterPath: String? = null
)