package com.example.android.databinding.basicsample

import android.app.Application
import com.example.android.databinding.basicsample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TMDBApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TMDBApplication)
            modules(appModule)
        }
    }


}