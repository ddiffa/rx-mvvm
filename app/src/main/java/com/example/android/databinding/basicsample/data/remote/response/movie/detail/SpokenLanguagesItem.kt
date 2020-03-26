package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SpokenLanguagesItem(
        @ColumnInfo(name = "name_spoken_language")
        @SerializedName("name")
        var name: String? = null,

        @ColumnInfo(name = "iso_spoken_language")
        @SerializedName("iso_639_1")
        var iso6391: String? = null,

        @ColumnInfo(name = "id_spoken_language")
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null
){
        @Ignore
        constructor(): this("","",0)
}