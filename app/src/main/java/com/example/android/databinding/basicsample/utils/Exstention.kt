package com.example.android.databinding.basicsample.utils

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun convertRuntime(runtime: String): String {
    val minute = runtime.toInt() % 60
    val hour = runtime.toInt() / 60

    return "$hour Hours $minute minutes"
}

const val getNewSDF: String = "EEEE, MMM d, yyyy"
const val getOldSdf: String = "yyyy-MM-dd"

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun loggingError(tag : String, msg : String){
    Log.e(tag,msg)
}

@SuppressLint("SimpleDateFormat")
fun getSimpleDate(date: String?): String? {
    val oldSdf = SimpleDateFormat(getOldSdf)
    val newSdf = SimpleDateFormat(getNewSDF, Locale.getDefault())
    return newSdf.format(oldSdf.parse(date))
}