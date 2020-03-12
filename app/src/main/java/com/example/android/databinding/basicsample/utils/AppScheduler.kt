package com.example.android.databinding.basicsample.utils

import io.reactivex.Scheduler

interface AppScheduler {
    fun ui() : Scheduler
    fun io() : Scheduler
}