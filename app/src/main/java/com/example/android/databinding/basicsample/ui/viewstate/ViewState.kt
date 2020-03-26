package com.example.android.databinding.basicsample.ui.viewstate

import com.example.android.databinding.basicsample.data.remote.response.error.ApiError

open class ViewState<T>(val data: T?, val err: ApiError?, val currentState: State) {
    companion object {
        fun <T> success(data: T?): ViewState<T> = ViewState(data, null, State.SUCCESS)

        fun <T> error(err: ApiError?): ViewState<T> = ViewState(null, err, State.FAILED)

        fun <T> loading(): ViewState<T> = ViewState(null, null, State.LOADING)
    }

    enum class State(value: Int) {
        LOADING(0), SUCCESS(1), FAILED(-1);

        var value: Int? = value
    }


}