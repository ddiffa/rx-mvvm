package com.example.android.databinding.basicsample.ui.viewmodel.viewstate

import com.example.android.databinding.basicsample.base.BaseViewState
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse

class TvShowDetailViewState(override var data: TvShowDetailResponse?, override var currentState: Int?, override var error: Throwable?) : BaseViewState<TvShowDetailResponse>() {
    companion object {
        val ERROR_STATE = TvShowDetailViewState(null, State.FAILED.value, Throwable())
        val LOADING_STATE = TvShowDetailViewState(null, State.LOADING.value, null)
        val SUCCESS_STATE = TvShowDetailViewState(TvShowDetailResponse(), State.SUCCESS.value, null)
    }
}