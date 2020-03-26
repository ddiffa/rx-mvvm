package com.example.android.databinding.basicsample.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android.databinding.basicsample.utils.Converter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvshowdb")
data class TvShowEntity(

        @field:SerializedName("first_air_date")
        val firstAirDate: String? = null,

        @field:SerializedName("overview")
        val overview: String? = null,

        @field:SerializedName("original_language")
        val originalLanguage: String? = null,

        @field:SerializedName("genre_ids")
        @TypeConverters(Converter::class)
        val genreIds: List<String>,

        @field:SerializedName("poster_path")
        val posterPath: String? = null,

        @field:SerializedName("origin_country")
        @TypeConverters(Converter::class)
        val originCountry: List<String>,

        @field:SerializedName("backdrop_path")
        val backdropPath: String? = null,

        @field:SerializedName("original_name")
        val originalName: String? = null,

        @field:SerializedName("popularity")
        val popularity: Double? = null,

        @field:SerializedName("vote_average")
        val voteAverage: Float? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @PrimaryKey(autoGenerate = false)
        @field:SerializedName("id")
        val id: Long? = null,

        @field:SerializedName("vote_count")
        val voteCount: Long? = null
)