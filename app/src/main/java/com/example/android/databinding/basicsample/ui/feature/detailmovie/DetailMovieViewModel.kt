package com.example.android.databinding.basicsample.ui.feature.detailmovie

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.repository.MovieRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import java.util.concurrent.TimeUnit

class DetailMovieViewModel(private val repository: MovieRepositoryImpl,
                           private var scheduler: SchedulerProviders) : BaseViewModel() {

    val movieDetailState = MutableLiveData<ViewState<MovieDetailEntity>>()
    val favoriteState = MutableLiveData<ViewState<Int>>()

    fun getMoviesDetail(apiKey: String, id: String) {
        EspressoIdlingResource.increment()
        repository.getMovieDataDetail(apiKey, id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .delay(2, TimeUnit.SECONDS)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe(
                        {
                            onSuccess(it)
                        }, {
                    onError(it)
                }
                ).also { compositeDisposable.add(it) }
    }

    @SuppressLint("CheckResult")
    fun updateMovieDetail(isFavorite: Boolean, movie: MovieDetailEntity) {
        EspressoIdlingResource.increment()
        repository.updateMovieDetail(isFavorite, movie)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .doOnSuccess {
                    EspressoIdlingResource.decrement()
                }
                .subscribe({
                    onFavoriteSuccess(it)
                }, {
                    onFavoriteError(it)
                }).also { compositeDisposable.add(it) }
    }

    private fun onFavoriteSuccess(int: Int) {
        favoriteState.postValue(ViewState.success(int))
    }

    private fun onFavoriteError(throwable: Throwable) {
        favoriteState.postValue(ViewState.error(throwable))
    }

    private fun onSuccess(movie: MovieDetailEntity) {
        movieDetailState.postValue(ViewState.success(movie))
    }

    private fun onError(throwable: Throwable) {
        movieDetailState.postValue(ViewState.error(throwable))
    }

    private fun onLoading() {
        movieDetailState.postValue(ViewState.loading())
    }

}