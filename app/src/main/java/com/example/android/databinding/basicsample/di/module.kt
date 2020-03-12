package com.example.android.databinding.basicsample.di

import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.ui.feature.movie.MovieFragment
import com.example.android.databinding.basicsample.ui.feature.tvshow.TVShowFragment
import com.example.android.databinding.basicsample.ui.viewmodel.MovieDetailViewModel
import com.example.android.databinding.basicsample.ui.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.ui.viewmodel.TvShowDetailViewModel
import com.example.android.databinding.basicsample.ui.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.MovieDetailViewState
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.MovieViewState
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.TvShowDetailViewState
import com.example.android.databinding.basicsample.ui.viewmodel.viewstate.TvShowViewState
import com.example.android.databinding.basicsample.utils.SchedulersProvider
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

    single { SchedulersProvider() }

    factory { MovieRepositoryImpl(get()) }
    factory { TvShowRepositoryImpl(get()) }

    factory { MovieDetailViewState(get(), get(), get()) }
    factory { MovieViewState(get(), get(), get()) }
    factory { TvShowViewState(get(), get(), get()) }
    factory { TvShowDetailViewState(get(), get(), get()) }

}