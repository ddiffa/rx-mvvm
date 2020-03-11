package com.example.android.databinding.basicsample

import android.app.Application
import com.example.android.databinding.basicsample.di.appModule
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }


}