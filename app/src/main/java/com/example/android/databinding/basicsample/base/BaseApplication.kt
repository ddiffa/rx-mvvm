package com.example.android.databinding.basicsample.base

import android.app.Application
import com.example.android.databinding.basicsample.di.appModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            modules(appModule)
        }
    }


}