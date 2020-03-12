package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.TvShowDetailViewState

class TvShowDetailViewModel(val tvShowRepositoryImpl: TvShowRepositoryImpl) : ViewModel() {

    val tvDetailState = MutableLiveData<TvShowDetailViewState>()

    fun getTvShowDetail(apiKey: String, id: String) {

        tvShowRepositoryImpl.getTvShowDetail(apiKey, id, {
            TvShowDetailViewState.SUCCESS_STATE.data = it
            tvDetailState.postValue(TvShowDetailViewState.SUCCESS_STATE)
        }, {
            TvShowDetailViewState.ERROR_STATE.error = it
            tvDetailState.postValue(TvShowDetailViewState.ERROR_STATE)
        }, {
            tvDetailState.postValue(TvShowDetailViewState.LOADING_STATE)
        })
    }
}