package com.example.android.databinding.basicsample.ui.feature.detailmovie

import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiDisposable
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.EspressoIdlingResource
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import java.util.concurrent.TimeUnit

class DetailMovieViewModel(private val repository: MovieRepositoryImpl,
                           private var scheduler: SchedulerProviders) : BaseViewModel() {

    val movieDetailState = MutableLiveData<ViewState<MovieDetailEntity>>()

    fun getMoviesDetail(apiKey: String, id: String) {
        EspressoIdlingResource.increment()
        repository.getMovieDataDetail(apiKey, id)
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .delay(2, TimeUnit.SECONDS)
                .debounce(400, TimeUnit.MILLISECONDS)
                .doOnComplete {
                    EspressoIdlingResource.decrement()
                }
                .subscribeWith(
                        ApiDisposable<MovieDetailEntity>(
                                {
                                    movieDetailState.postValue(ViewState.success(it))
                                }, {
                            movieDetailState.postValue(ViewState.error(it))
                        }
                        )
                ).also { compositeDisposable.add(it) }
    }

}