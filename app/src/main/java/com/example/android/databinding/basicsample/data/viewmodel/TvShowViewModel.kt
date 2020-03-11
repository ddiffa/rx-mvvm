package com.example.android.databinding.basicsample.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.contract.TvShowContract
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import org.koin.dsl.module

class TvShowViewModel(private val tvShowRepositoryImpl: TvShowRepositoryImpl) : ViewModel(), TvShowContract.ViewModel {

    val _isLoading = MutableLiveData<Boolean>()
    val _isError = MutableLiveData<Throwable>()
    val _tvShows = MutableLiveData<TvShowResponse>()
    val _tvShowDetail = MutableLiveData<TvShowDetailResponse>()

    override fun getTvShow(apiKey: String) {
        _isLoading.postValue(true)
        tvShowRepositoryImpl.getTvShow(apiKey, {
            _isLoading.postValue(false)
            _tvShows.postValue(it)
        }, {
            _isLoading.postValue(false)
            _isError.postValue(it)
        })
    }

    override fun getTvShowDetail(apiKey: String, id: String) {
        _isLoading.postValue(true)
        tvShowRepositoryImpl.getTvShowDetail(apiKey, id, {
            _isLoading.postValue(false)
            _tvShowDetail.postValue(it)
        }, {
            _isLoading.postValue(false)
            _isError.postValue(it)
        })
    }

}