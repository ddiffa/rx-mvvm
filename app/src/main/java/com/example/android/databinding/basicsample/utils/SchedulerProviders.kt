package com.example.android.databinding.basicsample.utils

import io.reactivex.Scheduler


interface SchedulerProviders {
    fun ui(): Scheduler
    fun io(): Scheduler
    fun computation() : Scheduler
}