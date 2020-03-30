package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface MovieRepository {

    fun getMovieData(apiKey: String): Observable<List<MovieEntity>>

    fun getFavoriteMovies()

    fun getMovieDataDetail(apiKey: String, id: String): Observable<MovieDetailEntity>

    fun updateMovieDetail(isFavorite : Boolean,movie : MovieDetailEntity)
}