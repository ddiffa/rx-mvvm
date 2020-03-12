package com.example.android.databinding.basicsample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.MovieViewState


class MovieViewModel(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {

    val movieListState = MutableLiveData<MovieViewState>()

    fun getMovies(apiKey: String) {
        movieRepositoryImpl.getMovieData(apiKey, {
            MovieViewState.SUCCESS_STATE.data = it
            movieListState.postValue(MovieViewState.SUCCESS_STATE)
        }, {
            MovieViewState.ERROR_STATE.error = it
            movieListState.postValue(MovieViewState.ERROR_STATE)
        }, {
            movieListState.postValue(MovieViewState.LOADING_STATE)
        })
    }

}