package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.TvShowViewState

class TvShowViewModel(private val tvShowRepositoryImpl: TvShowRepositoryImpl) : ViewModel() {

    val tvShowListState = MutableLiveData<TvShowViewState>()

    fun getTvShow(apiKey: String) {

        tvShowRepositoryImpl.getTvShow(apiKey, {
            TvShowViewState.SUCCESS_STATE.data = it
            tvShowListState.postValue(TvShowViewState.SUCCESS_STATE)
        }, {
            TvShowViewState.ERROR_STATE.error = it
            tvShowListState.postValue(TvShowViewState.ERROR_STATE)
        }, {
            tvShowListState.postValue(TvShowViewState.LOADING_STATE)
        })
    }


}