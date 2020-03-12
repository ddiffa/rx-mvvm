package com.example.android.databinding.basicsample.ui.viewmodel.viewstate

import com.example.android.databinding.basicsample.base.BaseViewState
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse

class TvShowViewState(override var data: TvShowResponse?, override var currentState: Int?, override var error: Throwable?) : BaseViewState<TvShowResponse>() {
    companion object {
        val ERROR_STATE = TvShowViewState(null, State.FAILED.value, Throwable())
        val LOADING_STATE = TvShowViewState(null, State.LOADING.value, null)
        val SUCCESS_STATE = TvShowViewState(TvShowResponse(), State.SUCCESS.value, null)
    }
}