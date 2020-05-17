package com.example.android.databinding.basicsample.ui.feature.tvshow

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.repository.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.EspressoIdlingResource
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import java.util.concurrent.TimeUnit

class TvShowViewModel(private val repository: TvShowRepositoryImpl,
                      private var scheduler: SchedulerProviders) : BaseViewModel() {

    val tvShowListState = MutableLiveData<ViewState<List<TvShowEntity>>>()

    @SuppressLint("CheckResult")
    fun getTvShow(apiKey: String) {
        EspressoIdlingResource.increment()
        repository.getTvShowData(apiKey)
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

    private fun onLoading() {
        tvShowListState.postValue(ViewState.loading())
    }

    private fun onSuccess(tvShows: List<TvShowEntity>) {
        tvShowListState.postValue(ViewState.success(tvShows))
    }

    private fun onError(err: Throwable) {
        tvShowListState.postValue(ViewState.error(err))
    }

}