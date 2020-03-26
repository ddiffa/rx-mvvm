package com.example.android.databinding.basicsample.data.source.impl

import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.remote.response.error.ApiDisposable
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.source.TvShowRepository
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
import com.example.android.databinding.basicsample.utils.loggingError
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class TvShowRepositoryImpl(private val remote: TMDBapi,
                           private val local: LocalDataSource,
                           private val schedulers: RxSingleSchedulers) : TvShowRepository {

    override fun getTvShowData(apiKey: String, onSuccess: (List<TvShowEntity>) -> Unit, onError: (ApiError) -> Unit, onTerminate: () -> Unit, onLoading: () -> Unit): Disposable {
        EspressoIdlingResource.increment()
        return Observable.concat(getTvShowDataFromDB(), getTvShowDataFromAPI(apiKey))
                .observeOn(AndroidSchedulers.mainThread(), true)
                .doOnTerminate(onTerminate)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .delay(2, TimeUnit.SECONDS)
                .debounce(400, TimeUnit.MILLISECONDS)
                .retry()
                .subscribeWith(
                        ApiDisposable<List<TvShowEntity>>(
                                {
                                    onSuccess(it)
                                }, {
                            onError(it)
                        }
                        )
                )

    }


    private fun getTvShowDataFromAPI(apiKey: String): Observable<List<TvShowEntity>> =
            remote.getTvShows(apiKey)
                    .map { tvShowResponse ->
                        Observable.create { _ : ObservableEmitter<Any?> ->
                            local.saveTvShowData(tvShowResponse.results)
                        }.subscribeOn(Schedulers.computation()).subscribe()
                        tvShowResponse.results
                    }
                    .subscribeOn(Schedulers.io())

    private fun getTvShowDataFromDB(): Observable<List<TvShowEntity>> =
            local.getAllTvShowData()
                    .subscribeOn(Schedulers.computation())
                    .doOnNext {
                        loggingError(TvShowRepositoryImpl::class.java.simpleName, it.size.toString())
                    }
                    .filter {
                        it.isNotEmpty()
                    }


    override fun favoriteTvShows(tvShow: TvShowDetailResponse) {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetail(apiKey: String, id: String, onSuccess: (TvShowDetailResponse) -> Unit, onError: (Throwable) -> Unit, onLoading: () -> Unit) {

    }

}