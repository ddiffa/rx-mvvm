package com.example.android.databinding.basicsample

import android.app.Application
import com.example.android.databinding.basicsample.di.appModule
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            printLogger()
            modules(appModule)
        }
    }


}