package com.example.android.databinding.basicsample.data.source.impl

import android.annotation.SuppressLint
import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.remote.response.error.ApiDisposable
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.MovieRepository
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
import com.example.android.databinding.basicsample.utils.loggingError
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class MovieRepositoryImpl(
        private val remote: TMDBapi,
        private val local: LocalDataSource,
        private val schedulers: RxSingleSchedulers
) : MovieRepository {

    override fun getMovieData(apiKey: String, onSuccess: (List<MovieEntity>) -> Unit, onError: (ApiError) -> Unit, terminate: () -> Unit, onLoading: () -> Unit): Disposable {
        EspressoIdlingResource.increment()
        return Observable.concat(getMovieDataFromDb(), getMovieDataFromApi(apiKey))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(terminate)
                .doOnNext {
                    onLoading()
                }
                .delay(2, TimeUnit.SECONDS)
                .debounce(400, TimeUnit.MILLISECONDS)
                .retry()
                .doOnComplete { EspressoIdlingResource.decrement() }
                .subscribeWith(
                        ApiDisposable<List<MovieEntity>>(
                                {
                                    onSuccess(it)
                                },
                                {
                                    onError(it)
                                }
                        )
                )
    }


    private fun getMovieDataFromApi(apiKey: String): Observable<List<MovieEntity>> =
            remote.getMovies(apiKey)
                    .map { movieResponse ->

                        Observable.create { _: ObservableEmitter<Any?> ->
                            local.saveMovieData(movieResponse.results)
                        }.subscribeOn(Schedulers.computation()).subscribe()
                        movieResponse.results
                    }
                    .subscribeOn(Schedulers.io())


    private fun getMovieDataFromDb(): Observable<List<MovieEntity>> =
            local.getAllMovieData()
                    .subscribeOn(Schedulers.computation())
                    .doOnNext {
                        loggingError(MovieRepositoryImpl::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }


    @SuppressLint("CheckResult")
    override fun saveMovieDataDb(movies: MovieResponse): Disposable {
        return CompositeDisposable()
    }


    override fun getFavoriteMovies() {

    }

    override fun getMovieDataDetail(apiKey: String, id: String): Single<MovieDetailResponse> =
            remote.getMovieDetail(id, apiKey).map { it }

    override fun getMovieDataDetailDB(id: String): Single<MovieDetailResponse> =
            local.getMovieDetail(id)

    override fun saveMovieDetail(movie: MovieDetailResponse) {
        local.saveMovieDetail(movie)
    }


//
//    @SuppressLint("CheckResult")
//    override fun getMovieData(apiKey: String, onSuccess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {
//
//        EspressoIdlingResource.increment()
//        MovieAPI.INSTANCE.getMovies(apiKey)
//                .compose(schedulersProvider.applySchedulers())
//                .delay(3, TimeUnit.SECONDS)
//                .doOnEach {
//                    if (it.isOnNext) {
//                        onLoading
//                    }
//                }
//                .doOnComplete {
//                    EspressoIdlingResource.decrement()
//                }
//                .subscribe(onSuccess, onError)
//    }
//
//    @SuppressLint("CheckResult")
//    override fun getMovieDataDetail(apiKey: String, id: String, onSuccess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {
//        EspressoIdlingResource.increment()
//        MovieAPI.INSTANCE.getMovieDetail(id, apiKey)
//                .delay(3, TimeUnit.SECONDS)
//                .compose(schedulersProvider.applySchedulers())
//                .doOnEach {
//                    if (it.isOnNext) {
//                        onLoading
//                    }
//                }
//                .doOnComplete {
//                    EspressoIdlingResource.decrement()
//                }
//                .subscribe(onSuccess, onError)
//    }


}