package com.example.android.databinding.basicsample.ui.feature.movie

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiDisposable
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.AppScheduler
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import java.util.concurrent.TimeUnit


class MovieViewModel(private val repositoryImpl: MovieRepositoryImpl,
                     private val scheduler: AppScheduler) : BaseViewModel() {

    val movieListState = MutableLiveData<ViewState<List<MovieEntity>>>()

    @SuppressLint("CheckResult")
    fun getMovies(apiKey: String) {
        EspressoIdlingResource.increment()
        repositoryImpl.getMovieData(apiKey)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .delay(2, TimeUnit.SECONDS)
                .debounce(400, TimeUnit.MILLISECONDS)
                .doOnNext {
                    onLoading()
                }
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribeWith(
                        ApiDisposable<List<MovieEntity>>({
                            onSuccess(it)
                        }, {
                            onError(it)
                        })
                ).also { compositeDisposable.add(it) }
    }

    private fun onSuccess(movies: List<MovieEntity>) {
        movieListState.postValue(ViewState.success(movies))
    }

    private fun onError(err: ApiError) {
        movieListState.postValue(ViewState.error(err))
    }

    private fun onLoading() {
        movieListState.postValue(ViewState.loading())
    }

}
