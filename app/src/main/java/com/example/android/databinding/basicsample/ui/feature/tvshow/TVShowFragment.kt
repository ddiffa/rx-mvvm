package com.example.android.databinding.basicsample.ui.feature.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.databinding.FragmentTvshowBinding
import com.example.android.databinding.basicsample.ui.adapter.TvShowAdapter
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.loggingError
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    private lateinit var adapter: TvShowAdapter
    private val viewModel by viewModel<TvShowViewModel>()
    private val TAG = TVShowFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tvshow, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TvShowAdapter()
        binding.adapter = adapter
        viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd")
        observeDataChange()
    }

    private fun observeDataChange() {
        viewModel.tvShowListState.observe(viewLifecycleOwner, Observer {
            when (it.currentState) {
                ViewState.State.LOADING -> {
                    shimmerTvShow.visible()
                    shimmerTvShow.startShimmerAnimation()
                    rvTvShow.hide()
                }
                ViewState.State.SUCCESS -> {
                    shimmerTvShow.stopShimmerAnimation()
                    shimmerTvShow.hide()
                    rvTvShow.visible()
                    it.data?.let { tvShow -> observeTvShows(tvShow) }
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
            }
        })
    }

    private fun observeError(err: Throwable) {
        err.message?.let { loggingError(TAG, it) }
    }

    private fun observeTvShows(tvShows: List<TvShowEntity>) {
        adapter.setTvShows(tvShows)
    }

}
