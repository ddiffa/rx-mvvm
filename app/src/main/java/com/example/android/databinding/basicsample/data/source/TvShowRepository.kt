package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import io.reactivex.disposables.Disposable

interface TvShowRepository {

    fun getTvShowData(apiKey: String, onSuccess: (List<TvShowEntity>) -> Unit, onError: (ApiError) -> Unit, onTerminate : () -> Unit = {}, onLoading: () -> Unit): Disposable

    fun favoriteTvShows(tvShow: TvShowDetailResponse)

    fun getTvShowDetail(apiKey: String, id: String, onSuccess: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit)
}