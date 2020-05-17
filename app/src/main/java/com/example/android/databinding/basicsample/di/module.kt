package com.example.android.databinding.basicsample.di

import androidx.room.Room
import com.example.android.databinding.basicsample.data.local.TMDBdb
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.ApiService
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.repository.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.repository.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.detailmovie.DetailMovieViewModel
import com.example.android.databinding.basicsample.ui.feature.detailtvshow.DetailTvShowViewModel
import com.example.android.databinding.basicsample.ui.feature.favorite.FavoriteFragment
import com.example.android.databinding.basicsample.ui.feature.favorite.movie.MovieFavoriteFragment
import com.example.android.databinding.basicsample.ui.feature.favorite.movie.MovieFavoriteViewModel
import com.example.android.databinding.basicsample.ui.feature.favorite.tvshow.TvShowFavoriteFragment
import com.example.android.databinding.basicsample.ui.feature.favorite.tvshow.TvShowFavoriteViewModel
import com.example.android.databinding.basicsample.ui.feature.movie.MovieFragment
import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
import com.example.android.databinding.basicsample.ui.feature.tvshow.TVShowFragment
import com.example.android.databinding.basicsample.ui.feature.tvshow.TvShowViewModel
import com.example.android.databinding.basicsample.common.ViewState
import com.example.android.databinding.basicsample.domain.SchedulerProviders
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    viewModel { MovieViewModel(get(), get()) }
    viewModel { TvShowViewModel(get(), get()) }
    viewModel { DetailTvShowViewModel(get(), get()) }
    viewModel { DetailMovieViewModel(get(), get()) }
    viewModel { MovieFavoriteViewModel(get(), get()) }
    viewModel { TvShowFavoriteViewModel(get(), get()) }

    fragment { MovieFragment() }
    fragment { TVShowFragment() }
    fragment { FavoriteFragment() }
    fragment { MovieFavoriteFragment() }
    fragment { TvShowFavoriteFragment() }

    single { SchedulerProviders.DEFAULT }

    single { MovieRepositoryImpl(get(), get()) }
    single { TvShowRepositoryImpl(get(), get()) }

    single { LocalDataSourceImpl(get(), get()) }
    single { RemoteDataSourceImpl(get(), get(), get()) }

    single { ViewState }
    single {
        return@single OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }

    single {
        Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    single {
        createApiService<ApiService>(get())

    }

    single {
        Room.databaseBuilder(get(), TMDBdb::class.java, "tmdb_db").allowMainThreadQueries().build()
    }

    single {
        get<TMDBdb>().tmdbDao()
    }


}

inline fun <reified T> createApiService(retrofit: Retrofit): T = retrofit.create(T::class.java)