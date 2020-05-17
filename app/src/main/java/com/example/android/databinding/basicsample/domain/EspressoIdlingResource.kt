package com.example.android.databinding.basicsample.domain

import androidx.test.espresso.idling.CountingIdlingResource

interface EspressoIdlingResource {

    companion object {
        private const val RESOURCE = "GLOBAL"
        val idlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            idlingResource.increment()
        }

        fun decrement() {
            idlingResource.decrement()
        }
    }
}
