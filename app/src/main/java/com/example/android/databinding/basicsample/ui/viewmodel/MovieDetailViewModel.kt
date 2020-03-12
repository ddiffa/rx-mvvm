package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.MovieDetailViewState

class MovieDetailViewModel(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val movieDetailState = MutableLiveData<MovieDetailViewState>()

    fun getMoviesDetail(apiKey: String, id: String) {
        movieRepositoryImpl.getMovieDataDetail(apiKey, id, {
            MovieDetailViewState.SUCCESS_STATE.data = it
            movieDetailState.postValue(MovieDetailViewState.SUCCESS_STATE)
        }, {
            MovieDetailViewState.ERROR_STATE.error = it
            movieDetailState.postValue(MovieDetailViewState.ERROR_STATE)
        }, {
            movieDetailState.postValue(MovieDetailViewState.LOADING_STATE)
        })
    }
}