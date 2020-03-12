package com.example.android.databinding.basicsample.base

open class BaseViewState<T> {


    open var data: T? = null
    open var error: Throwable? = null
    open var currentState: Int? = 0

    enum class State(value: Int) {
        LOADING(0), SUCCESS(1), FAILED(-1);

        var value: Int? = value
    }
}