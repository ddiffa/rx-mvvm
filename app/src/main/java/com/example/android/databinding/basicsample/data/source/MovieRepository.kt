package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse

interface MovieRepository {

    fun getMovieData(apiKey: String, onSuccess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit)

    fun getMovieDataDetail(apiKey: String, id : String, onSuccess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit)
}