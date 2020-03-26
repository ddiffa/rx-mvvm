package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity
data class ProductionCompaniesItem (
        @ColumnInfo(name = "logo_production_companies")
        @SerializedName("logo_path")
        var logoPath: String? = null,

        @ColumnInfo(name = "name_production_companies")
        @SerializedName("name")
        var name: String? = null,

        @ColumnInfo(name = "id_production_companies")
        @SerializedName("id")
        var id: Long? = null,

        @ColumnInfo(name = "origin_country_production_companies")
        @SerializedName("origin_country")
        var originCountry: String? = null
){
        @Ignore
        constructor():this("","",0,"")
}