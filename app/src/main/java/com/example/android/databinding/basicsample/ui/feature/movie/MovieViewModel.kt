package com.example.android.databinding.basicsample.ui.feature.movie

import androidx.lifecycle.MutableLiveData
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.ui.viewstate.BaseViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState


class MovieViewModel(private val repositoryImpl: MovieRepositoryImpl) : BaseViewModel() {

    val movieListState = MutableLiveData<ViewState<List<MovieEntity>>>()

    fun getMovies(apiKey: String) {
        repositoryImpl.getMovieData(apiKey, {
            onSuccess(it)
        }, {
            onError(it)
        }, {

        }, {
            onLoading()
        }).also { compositeDisposable.add(it) }
    }

    private fun onSuccess(movies: List<MovieEntity>) {
        movieListState.postValue(ViewState.success(movies))
    }

    private fun onError(throwable: ApiError) {
        movieListState.postValue(ViewState.error(throwable))
    }

    private fun onLoading() {
        movieListState.postValue(ViewState.loading())
    }

}
