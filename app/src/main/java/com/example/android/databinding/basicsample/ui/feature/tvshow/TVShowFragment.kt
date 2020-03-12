package com.example.android.databinding.basicsample.ui.feature.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.databinding.FragmentTvshowBinding
import com.example.android.databinding.basicsample.ui.adapter.TvShowAdapter
import com.example.android.databinding.basicsample.ui.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment() {

    private lateinit var binding: FragmentTvshowBinding
    private lateinit var adapter: TvShowAdapter
    private val viewModel by viewModel<TvShowViewModel>()

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
                0 -> {
                    shimmerTvShow.visible()
                    rvTvShow.hide()
                }
                1 -> {
                    shimmerTvShow.stopShimmerAnimation()
                    shimmerTvShow.hide()
                    rvTvShow.visible()
                    it.data?.let { tvShow -> observeTvShows(tvShow) }
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

    private fun observeTvShows(tvShows: TvShowResponse) {
        tvShows.results?.let {
            adapter.setTvShows(it)
            adapter.notifyDataSetChanged()
        }
    }

}
