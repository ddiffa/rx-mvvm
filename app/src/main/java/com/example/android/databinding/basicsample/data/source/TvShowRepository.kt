package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse

interface TvShowRepository {

    fun getTvShow(apiKey: String, onSucces: (TvShowResponse) -> Unit, onError: (Throwable) -> Unit)

    fun getTvShowDetail(apiKey: String, id: String, onSucces: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit)
}