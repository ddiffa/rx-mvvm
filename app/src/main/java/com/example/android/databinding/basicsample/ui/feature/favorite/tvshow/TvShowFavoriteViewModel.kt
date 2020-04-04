package com.example.android.databinding.basicsample.ui.feature.favorite.tvshow

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import java.util.concurrent.TimeUnit

class TvShowFavoriteViewModel(private val repositoryImpl: TvShowRepositoryImpl,
                              private val scheduler: SchedulerProviders) : BaseViewModel() {

    val tvShowDetailState = MutableLiveData<ViewState<PagedList<TvShowDetailEntity>>>()

    @SuppressLint("CheckResult")
    fun getAllFavoriteTvShow(isFavorite: Boolean) {
        EspressoIdlingResource.increment()
        repositoryImpl.getAllFavoriteTvShow(isFavorite)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .delay(2, TimeUnit.SECONDS)
                .doOnNext {
                    onLoading()
                }
                .subscribe({
                    onSucces(it)
                }, {
                    onError(it)
                }).also { compositeDisposable.add(it) }
    }

    private fun onSucces(tvShow: PagedList<TvShowDetailEntity>) {
        tvShowDetailState.postValue(ViewState.success(tvShow))
        EspressoIdlingResource.decrement()
    }

    private fun onError(throwable: Throwable) {
        tvShowDetailState.postValue(ViewState.error(throwable))
        EspressoIdlingResource.decrement()
    }

    private fun onLoading() {
        tvShowDetailState.postValue(ViewState.loading())
    }

}