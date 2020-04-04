package com.example.android.databinding.basicsample.ui.feature.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.databinding.FragmentTvShowFavoriteBinding
import com.example.android.databinding.basicsample.ui.adapter.TvShowFavoriteAdapter
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.loggingError
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_tv_show_favorite.*
import org.koin.android.ext.android.inject

class TvShowFavoriteFragment : Fragment() {

    private val viewModel by inject<TvShowFavoriteViewModel>()
    private lateinit var binding: FragmentTvShowFavoriteBinding
    private lateinit var adapter: TvShowFavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TvShowFavoriteAdapter()
        binding.adapter = adapter
        viewModel.getAllFavoriteTvShow(true)

        observeDataChange()
    }

    private fun observeDataChange() {
        viewModel.tvShowDetailState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    shimmerTvShowFavorite.visible()
                    shimmerTvShowFavorite.startShimmerAnimation()
                    rvTvShowFavorite.hide()
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
                ViewState.State.SUCCESS -> {
                    shimmerTvShowFavorite.stopShimmerAnimation()
                    shimmerTvShowFavorite.hide()
                    it.data?.let { tvShow -> observeSuccess(tvShow) }
                }
            }
        })
    }

    private fun observeSuccess(tvShow: PagedList<TvShowDetailEntity>) {
        if (tvShow.size > 0) {
            rvTvShowFavorite.visible()
            lnTvShowZero.hide()
            adapter.submitList(tvShow)
        } else {
            lnTvShowZero.visible()
            rvTvShowFavorite.hide()
        }
    }

    private fun observeError(throwable: Throwable) {
        throwable.message?.let { loggingError(TvShowFavoriteFragment::class.java.simpleName, it) }
    }
}
