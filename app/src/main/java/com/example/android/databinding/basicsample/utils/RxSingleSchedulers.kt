package com.example.android.databinding.basicsample.utils

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface RxSingleSchedulers {
    fun <T> applySchedulers(): ObservableTransformer<T, T>?


    companion object {
        @JvmStatic
        val DEFAULT: RxSingleSchedulers = object : RxSingleSchedulers {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> {
                return ObservableTransformer { observable: Observable<T> ->
                    observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }

        @JvmStatic
        val TEST_SCHEDULER: RxSingleSchedulers = object : RxSingleSchedulers {
            override fun <T> applySchedulers(): ObservableTransformer<T, T> {
                return ObservableTransformer { observable: Observable<T> ->
                    observable
                            .subscribeOn(Schedulers.trampoline())
                            .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}