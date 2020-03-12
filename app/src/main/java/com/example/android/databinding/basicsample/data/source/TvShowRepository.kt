package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse

interface TvShowRepository {

    fun getTvShow(apiKey: String, onSuccess: (TvShowResponse) -> Unit, onError: (Throwable) -> Unit, onLoading : () -> Unit)

    fun getTvShowDetail(apiKey: String, id: String, onSuccess: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit)
}