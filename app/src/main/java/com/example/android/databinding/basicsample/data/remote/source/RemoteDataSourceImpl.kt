package com.example.android.databinding.basicsample.data.remote.source

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class RemoteDataSourceImpl(private val api: TMDBapi,
                           private val localImpl: LocalDataSourceImpl,
                           private val scheduler: SchedulerProviders) : RemoteDataSource {

    override fun getMovieDataFromApi(apiKey: String): Observable<List<MovieEntity>> =
            api.getMovies(apiKey)
                    .map { movieResponse ->

                        Observable.create { _: ObservableEmitter<Any?> ->
                            localImpl.saveMovieData(movieResponse.results)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        movieResponse.results
                    }
                    .subscribeOn(scheduler.io())

    override fun getMovieDataDetailFromApi(apiKey: String, id: String): Observable<MovieDetailEntity> =
            api.getMovieDetail(id, apiKey)
                    .map { movieDetailResponse ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            localImpl.saveMovieDetail(movieDetailResponse)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        movieDetailResponse
                    }
                    .subscribeOn(scheduler.io())

    override fun getTvShowDataFromAPI(apiKey: String): Observable<List<TvShowEntity>> =
            api.getTvShows(apiKey)
                    .map { tvShowResponse ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            localImpl.saveTvShowData(tvShowResponse.results)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        tvShowResponse.results
                    }
                    .subscribeOn(scheduler.io())

    override fun getTvShowDataDetailFromApi(apiKey: String, id: String): Observable<TvShowDetailEntity> =
            api.getTvShowDetail(id, apiKey)
                    .map { tvShowDetailEntity ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            localImpl.saveTvShowDetail(tvShowDetailEntity)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        tvShowDetailEntity
                    }.subscribeOn(scheduler.io())
}