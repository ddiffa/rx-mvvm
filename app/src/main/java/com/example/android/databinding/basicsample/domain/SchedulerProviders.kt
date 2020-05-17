package com.example.android.databinding.basicsample.domain

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface SchedulerProviders {
    fun io(): Scheduler?
    fun computation(): Scheduler?
    fun ui(): Scheduler?

    companion object {
        @JvmStatic
        val DEFAULT: SchedulerProviders = object : SchedulerProviders {
            override fun io(): Scheduler? = Schedulers.io()

            override fun computation(): Scheduler? = Schedulers.computation()
            override fun ui(): Scheduler? = AndroidSchedulers.mainThread()


        }

        @JvmStatic
        val TEST_SCHEDULER: SchedulerProviders = object : SchedulerProviders {
            override fun io(): Scheduler? = Schedulers.trampoline()

            override fun computation(): Scheduler? = Schedulers.trampoline()
            override fun ui(): Scheduler? = Schedulers.trampoline()
        }
    }
}