package com.example.android.databinding.basicsample.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProvidersTest : SchedulerProviders {
    override fun ui(): Scheduler =
            Schedulers.trampoline()

    override fun io(): Scheduler =
            Schedulers.trampoline()

    override fun computation(): Scheduler =
            Schedulers.trampoline()
}