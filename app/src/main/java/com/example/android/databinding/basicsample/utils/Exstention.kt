package com.example.android.databinding.basicsample.utils

import android.annotation.SuppressLint
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun convertRuntime(runtime: String): String {
    val minute = runtime.toInt() % 60
    val hour = runtime.toInt() / 60

    return "$hour Hours $minute minutes"
}

val getNewSDF: String = "EEEE, MMM d, yyyy"
val getOldSdf: String = "yyyy-MM-dd"

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

@SuppressLint("SimpleDateFormat")
fun getSimpleDate(date: String?): String? {
    val oldSdf = SimpleDateFormat(getOldSdf)
    val newSdf = SimpleDateFormat(getNewSDF, Locale.getDefault())
    val newDate = newSdf.format(oldSdf.parse(date))
    return newDate
}