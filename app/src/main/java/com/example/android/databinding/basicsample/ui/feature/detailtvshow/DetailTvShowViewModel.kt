package com.example.android.databinding.basicsample.ui.feature.detailtvshow

import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiDisposable
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import java.util.concurrent.TimeUnit

class DetailTvShowViewModel(private val repository: TvShowRepositoryImpl,
                            private var scheduler: SchedulerProviders) : BaseViewModel() {

    val tvDetailState = MutableLiveData<ViewState<TvShowDetailEntity>>()

    fun getTvShowDetail(apiKey: String, id: String) {
        EspressoIdlingResource.increment()
        repository.getTvShowDetail(apiKey, id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .delay(2, TimeUnit.SECONDS)
                .debounce(400, TimeUnit.MILLISECONDS)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribeWith(
                        ApiDisposable<TvShowDetailEntity>({
                            onSuccess(it)
                        }, {
                            onError(it)
                        })
                ).also { compositeDisposable.add(it) }
    }

    fun updateTvShowDetail(isFavorite: Boolean, tvShowDetail: TvShowDetailEntity) {
        repository.updateTvShowDetail(isFavorite, tvShowDetail)
    }

    private fun onLoading() {
        tvDetailState.postValue(ViewState.loading())
    }

    private fun onError(err: ApiError) {
        tvDetailState.postValue(ViewState.error(err))
    }

    private fun onSuccess(tvShowDetail: TvShowDetailEntity) {
        tvDetailState.postValue(ViewState.success(tvShowDetail))
    }
}