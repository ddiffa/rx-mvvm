package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface MovieRepository {

    fun getMovieData(apiKey: String, onSuccess: (List<MovieEntity>) -> Unit, onError: (ApiError) -> Unit, onTerminate: () -> Unit = {}, onLoading: () -> Unit): Disposable

    fun saveMovieDataDb(movies: MovieResponse): Disposable

    fun getFavoriteMovies()

    fun getMovieDataDetail(apiKey: String, id: String): Single<MovieDetailResponse>

    fun getMovieDataDetailDB(id: String): Single<MovieDetailResponse>

    fun saveMovieDetail(movie: MovieDetailResponse)
}