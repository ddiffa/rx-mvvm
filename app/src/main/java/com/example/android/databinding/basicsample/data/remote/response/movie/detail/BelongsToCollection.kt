package com.example.android.databinding.basicsample.data.remote.response.movie.detail

import com.google.gson.annotations.SerializedName

data class BelongsToCollection(

	@field:SerializedName("backdrop_path")
	val backdropPath: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null
)