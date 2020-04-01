package com.example.android.databinding.basicsample.ui.feature.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.databinding.FragmentMovieFavoriteBinding
import com.example.android.databinding.basicsample.ui.adapter.MovieFavoriteAdapter
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.loggingError
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_movie_favorite.*
import org.koin.android.ext.android.inject

class MovieFavoriteFragment : Fragment() {

    private val viewModel by inject<MovieFavoriteViewModel>()
    private lateinit var binding: FragmentMovieFavoriteBinding
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieFavoriteAdapter()
        binding.adapter = adapter
        viewModel.getAllFavoriteMovie(true)

        observeDataChange()
    }

    private fun observeDataChange() {
        viewModel.movieDetailState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    shimmerMovieFavorite.visible()
                    rvMovieFavorite.hide()
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
                ViewState.State.SUCCESS -> {
                    shimmerMovieFavorite.stopShimmerAnimation()
                    shimmerMovieFavorite.hide()
                    it.data?.let { movie -> observeSuccess(movie) }
                }
            }
        })
    }

    private fun observeSuccess(movies: PagedList<MovieDetailEntity>) {
        if (movies.size > 0) {
            adapter.submitList(movies)
            rvMovieFavorite.visible()
            lnMovieZero.hide()
        } else {
            lnMovieZero.visible()
            rvMovieFavorite.hide()
        }
    }

    private fun observeError(throwable: Throwable) {
        throwable.message?.let { loggingError(MovieFavoriteFragment::class.java.simpleName, it) }
    }

}
