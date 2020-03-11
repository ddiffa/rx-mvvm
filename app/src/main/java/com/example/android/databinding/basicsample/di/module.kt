package com.example.android.databinding.basicsample.di

import com.example.android.databinding.basicsample.data.remote.MovieAPI
import com.example.android.databinding.basicsample.data.source.impl.MovieRepositoryImpl
import com.example.android.databinding.basicsample.data.source.impl.TvShowRepositoryImpl
import com.example.android.databinding.basicsample.data.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.data.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.utils.SchedulersProvider
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }


    bean { MovieAPI.create() }
    bean { SchedulersProvider() }

    bean { MovieRepositoryImpl(get(), get()) }
    bean { TvShowRepositoryImpl(get(),get()) }
}