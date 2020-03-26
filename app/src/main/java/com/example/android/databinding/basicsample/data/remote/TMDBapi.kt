package com.example.android.databinding.basicsample.data.remote

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


enum class TMDBapi {
    INSTANCE;

    private var api: Api


    init {

        val okhttp = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(makeLoggingInterceptor(true))
                .build()

        api = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okhttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging;
    }

    fun getMovies(apiKey: String): Observable<MovieResponse> {
        return api.getMovies(apiKey)
    }

    fun getTvShows(apiKey: String): Observable<TvShowResponse> {
        return api.getTvShows(apiKey)
    }

    fun getTvShowDetail(id: String, apiKey: String): Observable<TvShowDetailResponse> {
        return api.getTvShowDetail(id, apiKey)
    }

    fun getMovieDetail(id: String, apiKey: String): Single<MovieDetailResponse> {
        return api.getMovieDetail(id, apiKey)
    }

    interface Api {

        @GET("movie/now_playing")
        fun getMovies(@Query("api_key") apiKey: String): Observable<MovieResponse>

        @GET("tv/popular")
        fun getTvShows(@Query("api_key") apiKey: String): Observable<TvShowResponse>

        @GET("tv/{id}")
        fun getTvShowDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Observable<TvShowDetailResponse>

        @GET("movie/{id}")
        fun getMovieDetail(@Path("id") id: String, @Query("api_key") apiKey: String): Single<MovieDetailResponse>

    }
}


