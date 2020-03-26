package com.example.android.databinding.basicsample.data.remote.response.tvshow.poular

import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.google.gson.annotations.SerializedName

data class TvShowResponse(

        @field:SerializedName("page")
	val page: Int? = null,

        @field:SerializedName("total_pages")
	val totalPages: Int? = null,

        @field:SerializedName("results")
	val results: List<TvShowEntity>,

        @field:SerializedName("total_results")
	val totalResults: Int? = null
)