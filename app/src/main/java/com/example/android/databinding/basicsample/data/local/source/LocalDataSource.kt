package com.example.android.databinding.basicsample.data.local.source

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable

interface LocalDataSource {
    fun saveMovieData(movies: List<MovieEntity>)

    fun saveTvShowData(tvShows: List<TvShowEntity>)

    fun saveMovieDetail(movieDetailEntity: MovieDetailEntity)

    fun saveTvShowDetail(tvShowDetailEntity: TvShowDetailEntity)

    fun getAllMovieData(): Observable<List<MovieEntity>>

    fun getTvShowData(): Observable<List<TvShowEntity>>

    fun getMovieDetail(id: String): Observable<MovieDetailEntity>

    fun getTvShowDetail(id: String): Observable<TvShowDetailEntity>

    fun updateMovieDetail(isFavorite : Boolean,movie : MovieDetailEntity)

    fun updateTvShowDetail(isFavorite: Boolean,tvShow : TvShowDetailEntity)
}