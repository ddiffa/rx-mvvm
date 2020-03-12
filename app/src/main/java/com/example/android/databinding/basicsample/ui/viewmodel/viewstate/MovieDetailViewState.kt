package com.example.android.databinding.basicsample.ui.viewmodel.viewstate

import com.example.android.databinding.basicsample.base.BaseViewState
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse

class MovieDetailViewState(override var data: MovieDetailResponse?, override var currentState: Int?, override var error: Throwable?) : BaseViewState<MovieDetailResponse>() {
    companion object {
        val ERROR_STATE = MovieDetailViewState(null, State.FAILED.value, Throwable())
        val LOADING_STATE = MovieDetailViewState(null, State.LOADING.value, null)
        val SUCCESS_STATE = MovieDetailViewState(MovieDetailResponse(), State.SUCCESS.value, null)
    }
}