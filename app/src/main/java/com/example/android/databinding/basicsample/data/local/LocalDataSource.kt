package com.example.android.databinding.basicsample.data.local

import com.example.android.databinding.basicsample.data.local.dao.TMDBDao
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import com.example.android.databinding.basicsample.utils.loggingError
import io.reactivex.Observable

class LocalDataSource(private val dao: TMDBDao,
                      private val scheduler: SchedulerProviders) {


    fun saveMovieData(movies: List<MovieEntity>) {
        for (movie in movies) {
            dao.insertMovie(movie)
        }
    }

    fun saveTvShowData(tvShows: List<TvShowEntity>) {
        for (tvShow in tvShows) {
            dao.insertTvShow(tvShow)
        }
    }

    fun saveMovieDetail(movieDetailEntity: MovieDetailEntity) {
        dao.insertMovieDetail(movieDetailEntity)
    }

    fun saveTvShowDetail(tvShowDetailEntity: TvShowDetailEntity) {
        dao.insertTvShowDetail(tvShowDetailEntity)
    }

    fun getAllMovieData(): Observable<List<MovieEntity>> =
            dao.getNowPlayingMovie()
                    .subscribeOn(scheduler.computation())
                    .doOnError {
                        loggingError(LocalDataSource::class.java.simpleName, it.localizedMessage)
                    }
                    .doOnNext {
                        loggingError(LocalDataSource::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }


    fun getTvShowData(): Observable<List<TvShowEntity>> =
            dao.getAllTvShowData()
                    .subscribeOn(scheduler.computation())
                    .doOnNext {
                        loggingError(TvShowRepositoryImpl::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }


    fun getMovieDetail(id: String): Observable<MovieDetailEntity> =
            dao.getMovieDetail(id)
                    .subscribeOn(scheduler.computation())
                    .filter { true }
                    .doOnNext {
                        it.title?.let { title -> loggingError(LocalDataSource::class.java.simpleName, title) }
                    }


    fun getTvShowDetail(id: String): Observable<TvShowDetailEntity> =
            dao.getTvShowDetail(id)
                    .subscribeOn(scheduler.computation())
                    .filter { true }
                    .doOnNext {
                        it.name?.let { title -> loggingError(LocalDataSource::class.java.simpleName, title) }
                    }
}