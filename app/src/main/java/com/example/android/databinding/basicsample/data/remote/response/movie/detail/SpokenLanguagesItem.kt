package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(
        @SerializedName("name")
        var name: String? = null,

        @SerializedName("iso_639_1")
        var iso6391: String? = null
)