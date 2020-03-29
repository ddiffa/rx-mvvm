package com.example.android.databinding.basicsample.data.local.entity

import androidx.room.*
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.GenresItem
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.*
import com.example.android.databinding.basicsample.utils.GenresConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tvshowdetail_db")
data class TvShowDetailEntity(

        @field:SerializedName("original_language")
        var originalLanguage: String? = null,

        @field:SerializedName("number_of_episodes")
        var numberOfEpisodes: Int? = null,

        @field:SerializedName("networks")
        @Ignore
        var networks: List<NetworksItem?>? = null,

        @field:SerializedName("type")
        var type: String? = null,

        @field:SerializedName("backdrop_path")
        var backdropPath: String? = null,

        @field:SerializedName("genres")
        @TypeConverters(GenresConverter::class)
        var genres: List<GenresItem?>? = null,

        @field:SerializedName("popularity")
        var popularity: Float? = null,

        @field:SerializedName("id")
        @PrimaryKey(autoGenerate = false)
        var id: Int? = null,

        @field:SerializedName("number_of_seasons")
        var numberOfSeasons: Int? = null,

        @field:SerializedName("vote_count")
        var voteCount: Int? = null,

        @field:SerializedName("first_air_date")
        var firstAirDate: String? = null,

        @field:SerializedName("overview")
        var overview: String? = null,

        @field:SerializedName("seasons")
        @Ignore
        var seasons: List<SeasonsItem?>? = null,

        @field:SerializedName("languages")
        @Ignore
        var languages: List<String?>? = null,

        @field:SerializedName("created_by")
        @Ignore
        var createdBy: List<CreatedByItem?>? = null,

        @field:SerializedName("last_episode_to_air")
        @Ignore
        var lastEpisodeToAir: LastEpisodeToAir? = null,

        @field:SerializedName("poster_path")
        var posterPath: String? = null,

        @field:SerializedName("origin_country")
        @Ignore
        var originCountry: List<String?>? = null,

        @field:SerializedName("production_companies")
        @Ignore
        var productionCompanies: List<ProductionCompaniesItem?>? = null,

        @field:SerializedName("original_name")
        var originalName: String? = null,

        @field:SerializedName("vote_average")
        var voteAverage: Float? = null,

        @field:SerializedName("name")
        var name: String? = null,

        @field:SerializedName("episode_run_time")
        @Ignore
        var episodeRunTime: List<Int?>? = null,

        @field:SerializedName("next_episode_to_air")
        @Ignore
        var nextEpisodeToAir: NextEpisodeToAir? = null,

        @field:SerializedName("in_production")
        var inProduction: Boolean? = null,

        @field:SerializedName("last_air_date")
        var lastAirDate: String? = null,

        @field:SerializedName("homepage")
        var homepage: String? = null,

        @field:SerializedName("status")
        var status: String? = null,

        @ColumnInfo(name = "isFavorite")
        var isFavorite : Boolean? = null
)