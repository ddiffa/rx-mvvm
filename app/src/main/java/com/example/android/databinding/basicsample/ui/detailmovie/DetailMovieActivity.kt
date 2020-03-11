package com.example.android.databinding.basicsample.ui.detailmovie

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.contract.MovieContract
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.databinding.ActivityDetailMovieBinding
import com.example.android.databinding.basicsample.utils.convertRuntime
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.android.architecture.ext.viewModel

class DetailMovieActivity : AppCompatActivity(), MovieContract.View {
    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel by viewModel<MovieViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_movie)

        viewModel.apply {
            _isLoading.observe(this@DetailMovieActivity, Observer {
                observeLoading(it)
            })

            _isError.observe(this@DetailMovieActivity, Observer {
                observeError(it)
            })

            _moviesDetail.observe(this@DetailMovieActivity, Observer {
                observeMoviesDetail(it)
            })
        }
        viewModel.getMoviesDetail("ac313fc1138a0ed697567a0dedddc6cd", intent.getStringExtra("data"))
        imgBack.setOnClickListener { onBackPressed() }
    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) {

            } else {

            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeMovies(movies: MovieResponse) {

    }

    override fun observeMoviesDetail(movies: MovieDetailResponse) {
        var genres: String = ""
        for (genre in movies.genres) {
            genres += genre.name.toString() + ", "
        }
        binding.moviesDetail = movies
        binding.imageBackdrop = movies.backdropPath
        binding.imageCirclePoster = movies.posterPath
        binding.tvRuntime = convertRuntime(movies.runtime.toString())
        binding.tvGenres = genres
    }
}
