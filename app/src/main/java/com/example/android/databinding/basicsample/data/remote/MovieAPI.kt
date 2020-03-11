package com.example.android.databinding.basicsample.data.remote

import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String): Observable<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(@Query("api_key") apiKey: String, @Path("id") id: String): Observable<TvShowDetailResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Query("api_key") apiKey: String, @Path("id") id: String): Observable<MovieDetailResponse>

    companion object Factory {
        fun create(): MovieAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(MovieAPI::class.java)
        }
    }

}


