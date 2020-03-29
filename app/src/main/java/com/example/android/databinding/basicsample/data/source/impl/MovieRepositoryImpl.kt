package com.example.android.databinding.basicsample.data.source.impl

import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.RemoteDataSource
import com.example.android.databinding.basicsample.data.source.MovieRepository
import io.reactivex.Observable


class MovieRepositoryImpl(
        private val remote: RemoteDataSource,
        private val local: LocalDataSource
) : MovieRepository {

    override fun getMovieData(apiKey: String): Observable<List<MovieEntity>> {
        return Observable.concatArrayEager(local.getAllMovieData(), remote.getMovieDataFromApi(apiKey))
    }

    override fun getFavoriteMovies() {

    }

    override fun getMovieDataDetail(apiKey: String, id: String): Observable<MovieDetailEntity> =
            Observable.concatArrayEager(local.getMovieDetail(id), remote.getMovieDataDetailFromApi(apiKey, id))

}