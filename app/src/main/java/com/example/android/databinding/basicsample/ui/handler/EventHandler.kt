package com.example.android.databinding.basicsample.ui.handler

import android.content.Context
import android.content.Intent
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.ui.feature.detailmovie.DetailMovieActivity
import com.example.android.databinding.basicsample.ui.feature.detailtvshow.DetailTVShowActivity

open class EventHandler(context: Context) {

    private val mContext: Context = context

    fun onMovieDetailsClick(id: String) {
        val intent = Intent(mContext, DetailMovieActivity::class.java)
        intent.putExtra("data", id)
        mContext.startActivity(intent)
    }

    fun onTvShowDetailsClick(id: String) {
        val intent = Intent(mContext, DetailTVShowActivity::class.java)
        intent.putExtra("data", id)
        mContext.startActivity(intent)
    }

    fun onFavoriteMovieClick(movie : MovieDetailEntity){

    }

}