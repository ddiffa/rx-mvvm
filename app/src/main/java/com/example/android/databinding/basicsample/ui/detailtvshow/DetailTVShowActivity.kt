package com.example.android.databinding.basicsample.ui.detailtvshow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.contract.TvShowContract
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.databinding.ActivityDetailTvshowBinding

class DetailTVShowActivity : AppCompatActivity(), TvShowContract.View {

    private lateinit var binding: ActivityDetailTvshowBinding
    private lateinit var viewModel: TvShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tvshow)
        viewModel = ViewModelProviders.of(this)[TvShowViewModel::class.java]

        viewModel.apply {
            _isError.observe(this@DetailTVShowActivity, Observer {
                observeError(it)
            })
            _isLoading.observe(this@DetailTVShowActivity, Observer {
                observeLoading(it)
            })
            _tvShowDetail.observe(this@DetailTVShowActivity, Observer {
                observeTvShowDetail(it)
            })
        }
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

    override fun observeTvShows(tvShows: TvShowResponse) {
        TODO("Not yet implemented")
    }

    override fun observeTvShowDetail(tvShow: TvShowDetailResponse) {
        var genres: String = ""
        for (genre in tvShow.genres) {
            genres += genre.name.toString() + ", "
        }
        binding.tvDetail = tvShow
        binding.imageBackdropTV = tvShow.backdropPath
        binding.imageCirclePosterTV = tvShow.posterPath
        binding.tvGenres = genres

    }
}
