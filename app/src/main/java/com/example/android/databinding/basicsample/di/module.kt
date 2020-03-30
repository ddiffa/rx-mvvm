package com.example.android.databinding.basicsample.di

import androidx.room.Room
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.local.TMDBdb
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.detailmovie.DetailMovieViewModel
import com.example.android.databinding.basicsample.ui.feature.detailtvshow.DetailTvShowViewModel
import com.example.android.databinding.basicsample.ui.feature.movie.MovieFragment
import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
import com.example.android.databinding.basicsample.ui.feature.tvshow.TVShowFragment
import com.example.android.databinding.basicsample.ui.feature.tvshow.TvShowViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.SchedulerProviders
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MovieViewModel(get(), get()) }
    viewModel { TvShowViewModel(get(), get()) }
    viewModel { DetailTvShowViewModel(get(), get()) }
    viewModel { DetailMovieViewModel(get(), get()) }

    fragment { MovieFragment() }
    fragment { TVShowFragment() }


    single { SchedulerProviders.DEFAULT}

    factory { MovieRepositoryImpl(get(), get()) }
    factory { TvShowRepositoryImpl(get(), get()) }

    factory { LocalDataSourceImpl(get(), get()) }
    factory { RemoteDataSourceImpl(get(), get(), get()) }

    single { ViewState }
    single { TMDBapi.INSTANCE }

    single {
        Room.databaseBuilder(get(), TMDBdb::class.java, "tmdb_db").allowMainThreadQueries().build()
    }

    single {
        get<TMDBdb>().tmdbDao()
    }
}