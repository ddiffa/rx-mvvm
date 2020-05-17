package com.example.android.databinding.basicsample.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.databinding.basicsample.R

@BindingAdapter("image")
fun loadImage(img: ImageView, imagePath: String?) {
    imagePath.let {
        Glide.with(img.context)
                .load("https://image.tmdb.org/t/p/w500" + it)
                .placeholder(R.drawable.play)
                .into(img)
    }
}

