package com.example.android.databinding.basicsample.ui.feature.tvshow

import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState

class TvShowViewModel(private val repositoryImpl: TvShowRepositoryImpl) : BaseViewModel() {

    val tvShowListState = MutableLiveData<ViewState<List<TvShowEntity>>>()

    fun getTvShow(apiKey: String) {
        repositoryImpl.getTvShowData(apiKey, {
            onSuccess(it)
        }, {
            onError(it)
        }, {

        }, {
            onLoading()
        }).also { compositeDisposable.add(it) }
    }

    private fun onLoading() {
        tvShowListState.postValue(ViewState.loading())
    }

    private fun onSuccess(tvShows: List<TvShowEntity>) {
        tvShowListState.postValue(ViewState.success(tvShows))
    }

    private fun onError(err: ApiError) {
        tvShowListState.postValue(ViewState.error(err))
    }

}