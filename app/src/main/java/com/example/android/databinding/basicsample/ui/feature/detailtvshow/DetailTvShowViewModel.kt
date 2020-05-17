package com.example.android.databinding.basicsample.ui.feature.detailtvshow

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.repository.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import java.util.concurrent.TimeUnit

class DetailTvShowViewModel(private val repository: TvShowRepositoryImpl,
                            private var scheduler: SchedulerProviders) : BaseViewModel() {

    val tvDetailState = MutableLiveData<ViewState<TvShowDetailEntity>>()
    val favoriteState = MutableLiveData<ViewState<Int>>()

    fun getTvShowDetail(apiKey: String, id: String) {
        EspressoIdlingResource.increment()
        repository.getTvShowDetail(apiKey, id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
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
                }
                ).also { compositeDisposable.add(it) }
    }

    @SuppressLint("CheckResult")
    fun updateTvShowDetail(isFavorite: Boolean, tvShowDetail: TvShowDetailEntity) {
        EspressoIdlingResource.increment()
        repository.updateTvShowDetail(isFavorite, tvShowDetail)
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

    private fun onLoading() {
        tvDetailState.postValue(ViewState.loading())
    }

    private fun onError(err: Throwable) {
        tvDetailState.postValue(ViewState.error(err))
    }

    private fun onSuccess(tvShowDetail: TvShowDetailEntity) {
        tvDetailState.postValue(ViewState.success(tvShowDetail))
    }
}