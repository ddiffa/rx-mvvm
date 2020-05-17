package com.example.android.databinding.basicsample.data.remote

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String): Observable<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Observable<TvShowDetailEntity>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Observable<MovieDetailEntity>

}


