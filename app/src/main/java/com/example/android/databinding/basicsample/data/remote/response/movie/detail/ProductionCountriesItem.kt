package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
 data class ProductionCountriesItem(

        @ColumnInfo(name = "iso_production_countries")
        @SerializedName("iso_3166_1")
        var iso31661: String? = null,

        @ColumnInfo(name = "name_production_countries")
        @SerializedName("name")
        var name: String? = null,

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id_production_countries")
        var id: Long? = null
){
       @Ignore
       constructor():this("","",0)
}