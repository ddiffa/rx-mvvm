package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse

interface MovieRepository {

    fun getMovieData(apiKey: String, onSucess: (MovieResponse) -> Unit, onError: (Throwable) -> Unit)

    fun getMovieDataDetail(apiKey: String, id : String, onSucess: (MovieDetailResponse) -> Unit, onError: (Throwable) -> Unit)
}