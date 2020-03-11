package com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying

import com.google.gson.annotations.SerializedName

data class Dates(

	@field:SerializedName("maximum")
	val maximum: String? = null,

	@field:SerializedName("minimum")
	val minimum: String? = null
)