package com.example.android.databinding.basicsample.ui.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.adapter.MovieAdapter
import com.example.android.databinding.basicsample.data.contract.MovieContract
import com.example.android.databinding.basicsample.data.remote.response.movie.detail.MovieDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.movie.nowplaying.MovieResponse
import com.example.android.databinding.basicsample.data.viewmodel.MovieViewModel
import com.example.android.databinding.basicsample.databinding.FragmentMovieBinding
import org.koin.android.architecture.ext.viewModel


class MovieFragment : Fragment(), MovieContract.View {

    private lateinit var binding: FragmentMovieBinding
    private val viewModel by viewModel<MovieViewModel>()

    private lateinit var adapter: MovieAdapter

    companion object {
        @JvmStatic
        fun getInstance(): Fragment {
            return MovieFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MovieAdapter()

        viewModel.apply {
            _isLoading.observe(this@MovieFragment, Observer<Boolean> {
                observeLoading(it)
            })
            _isError.observe(this@MovieFragment, Observer {
                observeError(it)
            })
            _movies.observe(this@MovieFragment, Observer {
                observeMovies(it)
            })
        }

        viewModel.getMovies("ac313fc1138a0ed697567a0dedddc6cd")
        binding.adapter = adapter
    }


    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) {

            } else {

            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeMovies(movies: MovieResponse) {
        movies?.results.let {
            adapter.setMovies(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun observeMoviesDetail(movies: MovieDetailResponse) {

    }


}
