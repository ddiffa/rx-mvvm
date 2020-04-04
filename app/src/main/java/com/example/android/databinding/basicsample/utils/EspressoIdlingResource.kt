package com.example.android.databinding.basicsample.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)
    fun increment() {
        loggingError("Idle", "Increment")
        idlingResource.increment()
    }

    fun decrement() {
        loggingError("Idle", "decrement")
        if (!idlingResource.isIdleNow) {
            idlingResource.decrement()
        }
    }
}
