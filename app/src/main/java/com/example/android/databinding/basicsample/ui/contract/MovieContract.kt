package com.example.android.databinding.basicsample.data.contract

import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse

interface MovieContract {

    interface View {
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeMovies(movies: MovieResponse)

        fun observeMoviesDetail(movies : MovieDetailResponse)
    }

    interface ViewModel {
        fun getMovies(apiKey: String)
        fun getMoviesDetail(apiKey: String, id : String)
    }

}