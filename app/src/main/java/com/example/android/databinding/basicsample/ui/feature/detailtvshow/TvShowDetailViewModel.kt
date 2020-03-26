package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.ViewState

class TvShowDetailViewModel(private val tvShowRepositoryImpl: TvShowRepositoryImpl) : ViewModel() {

    val tvDetailState = MutableLiveData<ViewState<TvShowDetailResponse>>()

    fun getTvShowDetail(apiKey: String, id: String) {

        tvShowRepositoryImpl.getTvShowDetail(apiKey, id, {
            tvDetailState.postValue(ViewState.success(it))
        }, {
            tvDetailState.postValue(ViewState.error(it))
        }, {
            tvDetailState.postValue(ViewState.loading())
        })
    }
}