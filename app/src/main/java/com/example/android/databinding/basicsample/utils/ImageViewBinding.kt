package com.example.android.databinding.basicsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(img: ImageView, posterPath: String) {
    Glide.with(img.context)
            .load("http://image.tmdb.org/t/p/w500" + posterPath)
            .into(img)
}