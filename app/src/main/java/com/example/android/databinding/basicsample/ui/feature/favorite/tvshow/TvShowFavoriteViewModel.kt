package com.example.android.databinding.basicsample.ui.feature.favorite.tvshow

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.repository.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import com.example.android.databinding.basicsample.domain.SchedulerProviders
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
                .debounce(400, TimeUnit.MILLISECONDS)
                .delay(2, TimeUnit.SECONDS)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribe({
                    onSucces(it)
                }, {
                    onError(it)
                }).also { compositeDisposable.add(it) }
    }

    private fun onSucces(tvShow: PagedList<TvShowDetailEntity>) {
        tvShowDetailState.postValue(ViewState.success(tvShow))
    }

    private fun onError(throwable: Throwable) {
        tvShowDetailState.postValue(ViewState.error(throwable))
    }

    private fun onLoading() {
        tvShowDetailState.postValue(ViewState.loading())
    }

}