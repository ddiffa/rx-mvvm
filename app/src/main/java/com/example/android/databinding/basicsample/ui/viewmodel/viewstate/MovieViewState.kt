package com.example.android.databinding.basicsample.ui.viewmodel.viewstate

import com.example.android.databinding.basicsample.base.BaseViewState
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse

class MovieViewState(override var data: MovieResponse?, override var currentState: Int?, override var error: Throwable?) : BaseViewState<MovieResponse>() {
    companion object {
        val ERROR_STATE = MovieViewState(null, State.FAILED.value, Throwable())
        val LOADING_STATE = MovieViewState(null, State.LOADING.value, null)
        val SUCCESS_STATE = MovieViewState(MovieResponse(), State.SUCCESS.value, null)
    }
}