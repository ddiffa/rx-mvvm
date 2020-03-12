package com.example.android.databinding.basicsample.data.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.data.contract.MovieContract
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import org.koin.dsl.module



class MovieViewModel(private val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel(), MovieContract.ViewModel {

    val _isLoading = MutableLiveData<Boolean>()
    val _isError = MutableLiveData<Throwable>()
    val _movies = MutableLiveData<MovieResponse>()
    val _moviesDetail = MutableLiveData<MovieDetailResponse>()

    override fun getMovies(apiKey: String) {
        _isLoading.postValue(true)
        movieRepositoryImpl.getMovieData(apiKey, {
            _isLoading.postValue(false)
            _movies.postValue(it)
        }, {
            _isLoading.postValue(false)
            _isError.postValue(it)
        })
    }

    override fun getMoviesDetail(apiKey: String, id: String) {
        _isLoading.postValue(true)
        movieRepositoryImpl.getMovieDataDetail(apiKey, id, {
            _isLoading.postValue(false)
            _moviesDetail.postValue(it)
        }, {
            _isLoading.postValue(false)
            _isError.postValue(it)
        })
    }

}