package com.example.android.databinding.basicsample.utils

fun convertRuntime(runtime: String): String {
    val minute = runtime.toInt() % 60
    val hour = runtime.toInt() / 60

    return "$hour Hours $minute minutes"
}