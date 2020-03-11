package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.source.TvShowRepository
import com.example.android.databinding.basicsample.utils.SchedulersProvider

class TvShowRepositoryImpl(private val api: MovieAPI,
                           private val schedulersProvider: SchedulersProvider) : TvShowRepository {
    @SuppressLint("CheckResult")
    override fun getTvShow(apiKey: String, onSucces: (TvShowResponse) -> Unit, onError: (Throwable) -> Unit) {
        api.getTvShows(apiKey)
                .observeOn(schedulersProvider.ui())
                .subscribeOn(schedulersProvider.io())
                .subscribe(onSucces, onError)
    }

    @SuppressLint("CheckResult")
    override fun getTvShowDetail(apiKey: String, id: String, onSucces: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit) {
        api.getTvShowDetail(id, apiKey)
                .observeOn(schedulersProvider.ui())
                .subscribeOn(schedulersProvider.io())
                .subscribe(onSucces, onError)
    }

}