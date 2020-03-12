package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.MovieRepository
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
import java.util.concurrent.TimeUnit


class MovieRepositoryImpl(private val schedulersProvider: RxSingleSchedulers) : MovieRepository {

    @SuppressLint("CheckResult")
    override fun getMovieData(apiKey: String, onSuccess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {

        EspressoIdlingResource.increment()
        MovieAPI.INSTANCE.getMovies(apiKey)
                .compose(schedulersProvider.applySchedulers())
                .delay(3, TimeUnit.SECONDS)
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
    override fun getMovieDataDetail(apiKey: String, id: String, onSuccess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {
        EspressoIdlingResource.increment()
        MovieAPI.INSTANCE.getMovieDetail(id, apiKey)
                .delay(3, TimeUnit.SECONDS)
                .compose(schedulersProvider.applySchedulers())
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