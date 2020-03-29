package com.example.android.databinding.basicsample.data.remote

import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.utils.AppScheduler
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class RemoteDataSource(private val api: TMDBapi,
                       private val local: LocalDataSource,
                       private val scheduler: AppScheduler) {

    fun getMovieDataFromApi(apiKey: String): Observable<List<MovieEntity>> =
            api.getMovies(apiKey)
                    .map { movieResponse ->

                        Observable.create { _: ObservableEmitter<Any?> ->
                            local.saveMovieData(movieResponse.results)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        movieResponse.results
                    }
                    .subscribeOn(scheduler.io())

    fun getMovieDataDetailFromApi(apiKey: String, id: String): Observable<MovieDetailEntity> =
            api.getMovieDetail(id, apiKey)
                    .map { movieDetailResponse ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            local.saveMovieDetail(movieDetailResponse)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        movieDetailResponse
                    }.subscribeOn(scheduler.io())

    fun getTvShowDataFromAPI(apiKey: String): Observable<List<TvShowEntity>> =
            api.getTvShows(apiKey)
                    .map { tvShowResponse ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            local.saveTvShowData(tvShowResponse.results)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        tvShowResponse.results
                    }
                    .subscribeOn(scheduler.io())

    fun getTvShowDataDetailFromApi(apiKey: String, id: String): Observable<TvShowDetailEntity> =
            api.getTvShowDetail(id, apiKey)
                    .map { tvShowDetailEntity ->
                        Observable.create { _: ObservableEmitter<Any?> ->
                            local.saveTvShowDetail(tvShowDetailEntity)
                        }.subscribeOn(scheduler.computation()).subscribe()
                        tvShowDetailEntity
                    }.subscribeOn(scheduler.io())
}