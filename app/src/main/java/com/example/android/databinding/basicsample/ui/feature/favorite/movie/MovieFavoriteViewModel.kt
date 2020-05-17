package com.example.android.databinding.basicsample.ui.feature.favorite.movie

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.repository.MovieRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import java.util.concurrent.TimeUnit

class MovieFavoriteViewModel(private val repositoryImpl: MovieRepositoryImpl,
                             private val scheduler: SchedulerProviders) : BaseViewModel() {

    val movieDetailState = MutableLiveData<ViewState<PagedList<MovieDetailEntity>>>()

    @SuppressLint("CheckResult")
    fun getAllFavoriteMovie(isFavorite: Boolean) {
        EspressoIdlingResource.increment()
        repositoryImpl.getAllFavoriteMovie(isFavorite)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .debounce(400, TimeUnit.MILLISECONDS)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe({
                    onSuccess(it)
                }, {
                    onError(it)
                }).also { compositeDisposable.add(it) }
    }

    private fun onLoading() {
        movieDetailState.postValue(ViewState.loading())
    }

    private fun onSuccess(movie: PagedList<MovieDetailEntity>) {
        movieDetailState.postValue(ViewState.success(movie))
    }

    private fun onError(throwable: Throwable) {
        movieDetailState.postValue(ViewState.error(throwable))
    }


}