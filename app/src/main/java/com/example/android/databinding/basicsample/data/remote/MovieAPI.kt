package com.example.android.databinding.basicsample.data.remote

import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface MovieAPI {

    @GET("movie/now_playing")
    fun getMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

    @GET("tv/popular")
    fun getTvShows(@Query("api_key") apiKey: String): Observable<TvShowResponse>

    @GET("tv/{id}")
    fun getTvShowDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Observable<TvShowDetailResponse>

    @GET("movie/{id}")
    fun getMovieDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Observable<MovieDetailResponse>

    companion object Factory {
        fun create(): MovieAPI {
            val okhttp = OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(6, TimeUnit.SECONDS)
                    .addInterceptor(makeLoggingInterceptor(true))
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.themoviedb.org/3/")
                    .client(okhttp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(MovieAPI::class.java)
        }

        private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            return logging;
        }

    }


}


