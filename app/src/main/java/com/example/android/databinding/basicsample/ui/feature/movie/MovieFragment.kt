package com.example.android.databinding.basicsample.ui.feature.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.databinding.FragmentMovieBinding
import com.example.android.databinding.basicsample.ui.adapter.MovieAdapter
import com.example.android.databinding.basicsample.ui.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModel<MovieViewModel>()

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = MovieAdapter()
        binding.adapter = adapter
        observeDataChange()
        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
    }

    private fun observeDataChange() {
        viewModel.movieListState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                0 -> {
                    shimmerMovie.visible()
                    rvMovie.hide()
                }
                1 -> {
                    shimmerMovie.stopShimmerAnimation()
                    shimmerMovie.hide()
                    rvMovie.visible()
                    it.data?.let { movies -> observeMovies(movies) }
                }
                -1 -> {
                    observeError(it.error)
                }
            }
        })
    }

    private fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
    }

    private fun observeMovies(movies: MovieResponse?) {
        movies?.results.let {
            adapter.setMovies(it)
        }
    }
}
