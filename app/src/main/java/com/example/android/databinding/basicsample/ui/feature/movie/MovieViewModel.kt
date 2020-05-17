package com.example.android.databinding.basicsample.ui.feature.movie

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.repository.MovieRepositoryImpl
import com.example.android.databinding.basicsample.base.BaseViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import com.example.android.databinding.basicsample.domain.idlingresource.EspressoIdlingResource
import java.util.concurrent.TimeUnit


class MovieViewModel(private val repositoryImpl: MovieRepositoryImpl,
                     private var scheduler: SchedulerProviders) : BaseViewModel() {

    val movieListState = MutableLiveData<ViewState<List<MovieEntity>>>()


    @SuppressLint("CheckResult")
    fun getMovies(apiKey: String) {
        EspressoIdlingResource.increment()
        repositoryImpl.getMovieData(apiKey)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .delay(2, TimeUnit.SECONDS)
                .doOnNext {
                    onLoading()

                }
                .subscribe({
                    onSuccess(it)
                }, {
                    onError(it)
                }
                ).also { compositeDisposable.add(it) }
    }

    private fun onSuccess(movies: List<MovieEntity>) {
        movieListState.postValue(ViewState.success(movies))
        EspressoIdlingResource.decrement()
    }

    private fun onError(err: Throwable) {
        movieListState.postValue(ViewState.error(err))
        EspressoIdlingResource.decrement()
    }

    private fun onLoading() {
        movieListState.postValue(ViewState.loading())
    }

}
