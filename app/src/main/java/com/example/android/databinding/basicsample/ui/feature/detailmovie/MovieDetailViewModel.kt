package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.ViewState
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl

class MovieDetailViewModel(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val movieDetailState = MutableLiveData<ViewState<MovieDetailResponse>>()

    fun getMoviesDetail(apiKey: String, id: String) {
        movieRepositoryImpl.getMovieDataDetail(apiKey, id, {
            movieDetailState.postValue(ViewState.success(it))
        }, {
            movieDetailState.postValue(ViewState.error(it))
        }, {
            movieDetailState.postValue(ViewState.loading())
        })
    }

}