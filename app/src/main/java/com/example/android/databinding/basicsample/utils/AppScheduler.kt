package com.example.android.databinding.basicsample.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppScheduler : SchedulerProviders {
    override fun ui(): Scheduler =
            AndroidSchedulers.mainThread()

    override fun io(): Scheduler =
            Schedulers.io()

    override fun computation(): Scheduler =
            Schedulers.computation()
}