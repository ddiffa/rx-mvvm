package com.example.android.databinding.basicsample.data.local.source

import com.example.android.databinding.basicsample.data.local.dao.TMDBDao
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import com.example.android.databinding.basicsample.utils.loggingError
import io.reactivex.Observable

class LocalDataSourceImpl(private val dao: TMDBDao,
                          private val scheduler: SchedulerProviders) : LocalDataSource {


    override fun saveMovieData(movies: List<MovieEntity>) {
        for (movie in movies) {
            dao.insertMovie(movie)
        }
    }

    override fun saveTvShowData(tvShows: List<TvShowEntity>) {
        for (tvShow in tvShows) {
            dao.insertTvShow(tvShow)
        }
    }

    override fun saveMovieDetail(movieDetailEntity: MovieDetailEntity) {
        movieDetailEntity.isFavorite = false
        dao.insertMovieDetail(movieDetailEntity)
    }

    override fun saveTvShowDetail(tvShowDetailEntity: TvShowDetailEntity) {
        tvShowDetailEntity.isFavorite = false
        dao.insertTvShowDetail(tvShowDetailEntity)
    }

    override fun getAllMovieData(): Observable<List<MovieEntity>> =
            dao.getNowPlayingMovie()
                    .subscribeOn(scheduler.computation())
                    .doOnError {
                        loggingError(LocalDataSourceImpl::class.java.simpleName, it.localizedMessage)
                    }
                    .doOnNext {
                        loggingError(LocalDataSourceImpl::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }

    override fun getTvShowData(): Observable<List<TvShowEntity>> =
            dao.getAllTvShowData()
                    .subscribeOn(scheduler.computation())
                    .doOnNext {
                        loggingError(TvShowRepositoryImpl::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }


    override fun getMovieDetail(id: String): Observable<MovieDetailEntity> =
            dao.getMovieDetail(id)
                    .subscribeOn(scheduler.computation())
                    .filter { true }
                    .doOnNext {
                        it.isFavorite?.let { title -> loggingError(LocalDataSourceImpl::class.java.simpleName, title.toString()) }
                    }


    override fun getTvShowDetail(id: String): Observable<TvShowDetailEntity> =
            dao.getTvShowDetail(id)
                    .subscribeOn(scheduler.computation())
                    .filter { true }
                    .doOnNext {
                        it.name?.let { title -> loggingError(LocalDataSourceImpl::class.java.simpleName, title) }
                    }

    override fun updateMovieDetail(isFavorite: Boolean, movie: MovieDetailEntity) {
        movie.isFavorite = isFavorite
        dao.updateMovieDetail(movie)
    }

    override fun updateTvShowDetail(isFavorite: Boolean,tvShow: TvShowDetailEntity) {
        tvShow.isFavorite = isFavorite
        dao.updateTvShowDetail(tvShow)
        loggingError(LocalDataSource::class.java.simpleName, "${tvShow.name} ${tvShow.isFavorite}")
    }
}