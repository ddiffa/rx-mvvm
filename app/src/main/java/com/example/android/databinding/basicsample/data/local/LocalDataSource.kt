package com.example.android.databinding.basicsample.data.local

import com.example.android.databinding.basicsample.data.local.dao.TMDBDao
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable
import io.reactivex.Single

class LocalDataSource(private val dao: TMDBDao) {

    fun saveMovieData(movies: List<MovieEntity>) {
        for (movie in movies) {
            dao.insertMovie(movie)
        }
    }

    fun getAllMovieData(): Observable<List<MovieEntity>> = dao.getNowPlayingMovie()

    fun saveTvShowData(tvShows: List<TvShowEntity>) {
        for (tvShow in tvShows){
            dao.insertTvShow(tvShow)
        }
    }

    fun getAllTvShowData(): Observable<List<TvShowEntity>> = dao.getAllTvShowData()

    fun saveMovieDetail(movieDetailResponse: MovieDetailResponse){
        dao.insertMovieDetail(movieDetailResponse)
    }

    fun getMovieDetail(id : String) : Single<MovieDetailResponse> = dao.getMovieDetail(id)
}