package com.example.android.databinding.basicsample.ui.feature.detailmovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.databinding.ActivityDetailMovieBinding
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.convertRuntime
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.loggingError
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel by viewModel<DetailMovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)
        viewModel.getMoviesDetail("ac313fc1138a0ed697567a0dedddc6cd", intent.getStringExtra("data"))
        imgBack.setOnClickListener { onBackPressed() }
        observeDataChange()
    }

    private fun observeDataChange() {
        viewModel.movieDetailState.observe(this, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    shimmerMovieDetail.visible()
                    layoutDataDetailMovie.hide()
                }
                ViewState.State.SUCCESS -> {
                    shimmerMovieDetail.stopShimmerAnimation()
                    shimmerMovieDetail.hide()
                    layoutDataDetailMovie.visible()
                    it.data?.let { movie -> observeMoviesDetail(movie) }
                }
                ViewState.State.FAILED -> {
                    it.err?.let { it1 -> observeError(it1) }
                }
            }
        })
    }

    private fun observeError(error: ApiError) {
        error?.logMessage?.let { loggingError(DetailMovieActivity::class.java.simpleName, it) }
        error?.let { Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }
    }

    private fun observeMoviesDetail(movies: MovieDetailEntity?) {
        var genres: String = ""
        for (genre in movies?.genres!!) {
            genres += genre?.name.toString() + ", "
        }
        binding.moviesDetail = movies
        binding.imageBackdropMovie = movies.backdropPath
        binding.imagePosterMovie = movies.posterPath
        binding.tvRuntime = convertRuntime(movies.runtime.toString())
        binding.tvGenres = genres
        binding.ratingMovie = movies.voteAverage?.div(2)
    }
}
