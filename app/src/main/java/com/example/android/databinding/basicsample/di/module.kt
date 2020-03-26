package com.example.android.databinding.basicsample.di

import androidx.room.Room
import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.TMDBdb
import com.example.android.databinding.basicsample.data.remote.TMDBapi
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.detailmovie.DetailMovieInteract
import com.example.android.databinding.basicsample.ui.feature.detailmovie.MovieDetailViewModel
import com.example.android.databinding.basicsample.ui.feature.detailtvshow.TvShowDetailViewModel
import com.example.android.databinding.basicsample.ui.feature.movie.MovieFragment
import com.example.android.databinding.basicsample.ui.feature.movie.MovieViewModel
import com.example.android.databinding.basicsample.ui.feature.tvshow.TVShowFragment
import com.example.android.databinding.basicsample.ui.feature.tvshow.TvShowViewModel
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.RxSingleSchedulers
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { TvShowDetailViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }

    fragment { MovieFragment() }
    fragment { TVShowFragment() }


    single { RxSingleSchedulers.DEFAULT }

    factory { MovieRepositoryImpl(get(), get(), get()) }
    factory { TvShowRepositoryImpl(get(),get(),get()) }

    factory { LocalDataSource(get()) }
    factory { DetailMovieInteract(get()) }

    single { ViewState }
    single { TMDBapi.INSTANCE }

    single {
        Room.databaseBuilder(get(), TMDBdb::class.java, "tmdb_db").build()
    }

    single {
        get<TMDBdb>().tmdbDao()
    }
}