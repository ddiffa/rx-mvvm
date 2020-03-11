package com.example.android.databinding.basicsample.ui.detailtvshow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.contract.TvShowContract
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.data.remote.response.tvshow.poular.TvShowResponse
import com.example.android.databinding.basicsample.data.viewmodel.TvShowViewModel
import com.example.android.databinding.basicsample.databinding.ActivityDetailTvshowBinding
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.activity_detail_movie.imgBack
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel



class DetailTVShowActivity : AppCompatActivity(), TvShowContract.View {

    private lateinit var binding: ActivityDetailTvshowBinding

    private val viewModel by viewModel<TvShowViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tvshow)
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
        viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", intent.getStringExtra("data"))
        imgBack.setOnClickListener { onBackPressed() }
    }

    override fun onResume() {
        super.onResume()
        layoutDataDetailTvShow.hide()
        shimmerTvShowDetail.startShimmerAnimation()
    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it) {
                layoutDataDetailTvShow.hide()
                shimmerTvShowDetail.visible()
            } else {
                shimmerTvShowDetail.stopShimmerAnimation()
                shimmerTvShowDetail.hide()
                layoutDataDetailTvShow.visible()
            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeTvShows(tvShows: TvShowResponse) {
    }

    override fun observeTvShowDetail(tvShow: TvShowDetailResponse) {
        var genres: String = ""
        for (genre in tvShow.genres) {
            genres += genre.name.toString() + ", "
        }
        binding.tvDetail = tvShow
        binding.imageBackdropTV = tvShow.backdropPath
        binding.tvGenres = genres
        binding.imageCirclePosterTV = tvShow.posterPath
        binding.rating = tvShow.voteAverage?.div(2)

    }
}
