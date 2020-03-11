package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.MovieRepository
import com.example.android.databinding.basicsample.utils.SchedulersProvider

class MovieRepositoryImpl(private val api: MovieAPI, private val schedulersProvider: SchedulersProvider) : MovieRepository {

    @SuppressLint("CheckResult")
    override fun getMovieData(apiKey: String, onSucess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit) {
        api.getMovies(apiKey)
                .observeOn(schedulersProvider.ui())
                .subscribeOn(schedulersProvider.io())
                .subscribe(onSucess, onError)
    }

    @SuppressLint("CheckResult")
    override fun getMovieDataDetail(apiKey: String, id: String, onSucess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit) {
        api.getMovieDetail(apiKey, id)
                .observeOn(schedulersProvider.ui())
                .subscribeOn(schedulersProvider.io())
                .subscribe(onSucess, onError)
    }


}