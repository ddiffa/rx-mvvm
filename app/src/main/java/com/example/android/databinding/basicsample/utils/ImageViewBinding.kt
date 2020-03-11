package com.example.android.databinding.basicsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(img: ImageView, imagePath: String?) {
    imagePath.let {
        Glide.with(img.context)
                .load("http://image.tmdb.org/t/p/w500" + it)
                .into(img)
    }
}
