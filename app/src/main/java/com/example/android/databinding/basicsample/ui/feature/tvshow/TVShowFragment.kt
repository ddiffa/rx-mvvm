package com.example.android.databinding.basicsample.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.adapter.TvShowAdapter
import com.example.android.databinding.basicsample.data.contract.TvShowContract
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.databinding.FragmentTvshowBinding
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TVShowFragment : Fragment(), TvShowContract.View {

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
        viewModel.apply {
            _isLoading.observe(viewLifecycleOwner, Observer {
                observeLoading(it)
            })
            _isError.observe(viewLifecycleOwner, Observer {
                observeError(it)
            })
            _tvShows.observe(viewLifecycleOwner, Observer {
                observeTvShows(it)
            })
        }

        viewModel.getTvShow("ac313fc1138a0ed697567a0dedddc6cd")
        binding.adapter = adapter
    }


    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) {
                shimmerTvShow.visible()
                rvTvShow.hide()
            } else {
                shimmerTvShow.stopShimmerAnimation()
                shimmerTvShow.hide()
                rvTvShow.visible()
            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeTvShows(tvShows: TvShowResponse) {
        tvShows.results?.let {
            adapter.setTvShows(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun observeTvShowDetail(tvShow: TvShowDetailResponse) {
        TODO("Not yet implemented")
    }


}
