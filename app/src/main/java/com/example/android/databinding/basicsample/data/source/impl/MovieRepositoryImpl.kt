package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.MovieRepository
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.SchedulersProvider
import org.koin.dsl.module
import java.util.concurrent.TimeUnit



class MovieRepositoryImpl(private val api: MovieAPI, private val schedulersProvider: SchedulersProvider) : MovieRepository {

    @SuppressLint("CheckResult")
    override fun getMovieData(apiKey: String, onSuccess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit) {
        EspressoIdlingResource.increment()
        api.getMovies(apiKey)
                .subscribeOn(schedulersProvider.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(schedulersProvider.ui())
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe(onSuccess, onError)
    }

    @SuppressLint("CheckResult")
    override fun getMovieDataDetail(apiKey: String, id: String, onSuccess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit) {
        EspressoIdlingResource.increment()
        api.getMovieDetail(id, apiKey)
                .subscribeOn(schedulersProvider.io())
                .delay(3, TimeUnit.SECONDS)
                .observeOn(schedulersProvider.ui())
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe(onSuccess, onError)
    }


}