package com.example.android.databinding.basicsample.data.local.entity

import androidx.room.*
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.*
import com.example.android.databinding.basicsample.utils.GenresConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "moviedetail_db")
data class MovieDetailEntity(

        @ColumnInfo(name = "original_language")
        @field:SerializedName("original_language")
        var originalLanguage: String? = "",

        @ColumnInfo(name = "imdb_id")
        @field:SerializedName("imdb_id")
        var imdbId: String? = "",

        @ColumnInfo(name = "video")
        @field:SerializedName("video")
        var video: Boolean? = null,

        @ColumnInfo(name = "title")
        @field:SerializedName("title")
        var title: String? = "",

        @ColumnInfo(name = "backdrop_path")
        @field:SerializedName("backdrop_path")
        var backdropPath: String? = "",

        @ColumnInfo(name = "revenue")
        @field:SerializedName("revenue")
        var revenue: Long? = 0,

        @TypeConverters(GenresConverter::class)
        @field:SerializedName("genres")
        var genres: List<GenresItem>? = emptyList(),

        @ColumnInfo(name = "popularity")
        @field:SerializedName("popularity")
        var popularity: Float? = 0f,

        @Ignore
        @field:SerializedName("production_countries")
        var productionCountries: List<ProductionCountriesItem>? = emptyList(),

        @PrimaryKey(autoGenerate = false)
        @field:SerializedName("id")
        var id: Long? = 0,

        @ColumnInfo(name = "vote_count")
        @field:SerializedName("vote_count")
        var voteCount: Long? = 0,

        @ColumnInfo(name = "budget")
        @field:SerializedName("budget")
        var budget: Long? = 0,

        @ColumnInfo(name = "overview")
        @field:SerializedName("overview")
        var overview: String? = "",

        @ColumnInfo(name = "original_title")
        @field:SerializedName("original_title")
        var originalTitle: String? = "",

        @ColumnInfo(name = "runtime")
        @field:SerializedName("runtime")
        var runtime: Long? = 0,

        @ColumnInfo(name = "poster_path")
        @field:SerializedName("poster_path")
        var posterPath: String? = "",

        @Ignore
        @field:SerializedName("spoken_languages")
        var spokenLanguages: List<SpokenLanguagesItem>? = emptyList(),


        @Ignore
        @field:SerializedName("production_companies")
        var productionCompanies: List<ProductionCompaniesItem>? = emptyList(),

        @ColumnInfo(name = "release_date")
        @field:SerializedName("release_date")
        var releaseDate: String? = "",

        @ColumnInfo(name = "vote_average")
        @field:SerializedName("vote_average")
        var voteAverage: Float? = 0f,

        @Ignore
        @SerializedName("belongs_to_collection")
        var belongsToCollection: BelongsToCollection? = null,

        @ColumnInfo(name = "tagline")
        @field:SerializedName("tagline")
        var tagline: String? = "",

        @ColumnInfo(name = "adult")
        @field:SerializedName("adult")
        var adult: Boolean? = null,

        @ColumnInfo(name = "homepage")
        @SerializedName("homepage")
        var homepage: String? = "",

        @ColumnInfo(name = "status")
        @SerializedName("status")
        var status: String? = "",

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean? = null
)