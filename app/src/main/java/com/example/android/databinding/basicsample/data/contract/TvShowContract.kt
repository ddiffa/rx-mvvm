package com.example.android.databinding.basicsample.data.contract

import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse

interface TvShowContract {
    interface View {
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeTvShows(tvShows: TvShowResponse)

        fun observeTvShowDetail(tvShow : TvShowDetailResponse)
    }

    interface ViewModel {
        fun getTvShow(apiKey: String)
        fun getTvShowDetail(apiKey: String, id : String)
    }
}