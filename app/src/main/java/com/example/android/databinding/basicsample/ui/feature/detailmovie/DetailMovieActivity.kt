package com.example.android.databinding.basicsample.ui.feature.detailmovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.databinding.ActivityDetailMovieBinding
import com.example.android.databinding.basicsample.ui.viewmodel.MovieDetailViewModel
import com.example.android.databinding.basicsample.utils.convertRuntime
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel by viewModel<MovieDetailViewModel>()
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
                0 -> {
                    shimmerMovieDetail.visible()
                    layoutDataDetailMovie.hide()
                }
                1 -> {
                    shimmerMovieDetail.stopShimmerAnimation()
                    shimmerMovieDetail.hide()
                    layoutDataDetailMovie.visible()
                    it.data?.let { movie -> observeMoviesDetail(movie) }
                }
                -1 -> {
                    observeError(it.error)
                }
            }
        })
    }

    private fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }
    }

    private fun observeMoviesDetail(movies: MovieDetailResponse) {
        var genres: String = ""
        if (movies.genres != null) {
            for (genre in movies.genres) {
                genres += genre?.name.toString() + ", "
            }
        }
        binding.moviesDetail = movies
        binding.imageBackdropMovie = movies.backdropPath
        binding.imagePosterMovie = movies.posterPath
        binding.tvRuntime = convertRuntime(movies.runtime.toString())
        binding.tvGenres = genres
        binding.ratingMovie = movies.voteAverage?.div(2)
    }
}
