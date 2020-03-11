package com.example.android.databinding.basicsample.di

import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.data.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.data.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.ui.movie.MovieFragment
import com.example.android.databinding.basicsample.ui.tvshow.TVShowFragment
import com.example.android.databinding.basicsample.utils.SchedulersProvider
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

    fragment { MovieFragment() }
    fragment { TVShowFragment() }

    single { MovieAPI.create() }
    single { SchedulersProvider() }

    factory { MovieRepositoryImpl(get(), get()) }
    factory { TvShowRepositoryImpl(get(), get()) }
}