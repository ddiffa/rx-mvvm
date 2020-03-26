package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class BelongsToCollection(

        @SerializedName("backdrop_path")
        @ColumnInfo(name = "backdrop_belongs_to_collection")
        var backdropPath: String? = null,

        @SerializedName("name")
        @ColumnInfo(name = "name_belongs_to_collection")
        var name: String? = null,

        @ColumnInfo(name = "id_belongs_to_collection")
        @SerializedName("id")
        @PrimaryKey(autoGenerate = false)
        var id: Long? = null,

        @SerializedName("poster_path")
        @ColumnInfo(name = "poster_belongs_to_collection")
        var posterPath: String? = null
) {
    @Ignore
    constructor() : this("", "", 0, "")
}