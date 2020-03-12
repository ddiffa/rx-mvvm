package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.source.TvShowRepository
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.SchedulersProvider
import java.util.concurrent.TimeUnit


class TvShowRepositoryImpl(
        private val schedulersProvider: SchedulersProvider) : TvShowRepository {
    @SuppressLint("CheckResult")
    override fun getTvShow(apiKey: String, onSuccess: (TvShowResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {
        EspressoIdlingResource.increment()
        MovieAPI.INSTANCE.getTvShows(apiKey)
                .subscribeOn(schedulersProvider.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(schedulersProvider.ui())
                .doOnEach {
                    if (it.isOnNext) {
                        onLoading
                    }
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe(onSuccess, onError)
    }

    @SuppressLint("CheckResult")
    override fun getTvShowDetail(apiKey: String, id: String, onSuccess: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {
        EspressoIdlingResource.increment()
        MovieAPI.INSTANCE.getTvShowDetail(id, apiKey)
                .subscribeOn(schedulersProvider.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(schedulersProvider.ui())
                .doOnEach {
                    if (it.isOnNext) {
                        onLoading
                    }
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe(onSuccess, onError)
    }

}