package com.example.android.databinding.basicsample.ui.feature.detailtvshow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.remote.response.tvshow.detail.TvShowDetailResponse
import com.example.android.databinding.basicsample.databinding.ActivityDetailTvshowBinding
import com.example.android.databinding.basicsample.ui.viewmodel.TvShowDetailViewModel
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.activity_detail_movie.imgBack
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailTVShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvshowBinding

    private val viewModel by viewModel<TvShowDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_tvshow)
        viewModel.getTvShowDetail("ac313fc1138a0ed697567a0dedddc6cd", intent.getStringExtra("data"))
        imgBack.setOnClickListener { onBackPressed() }
        observeDataChange()
    }

    private fun observeDataChange() {
        viewModel.tvDetailState.observe(this, Observer {
            when (it.currentState) {
                0 -> {
                    layoutDataDetailTvShow.hide()
                    shimmerTvShowDetail.visible()
                }
                1 -> {
                    shimmerTvShowDetail.stopShimmerAnimation()
                    shimmerTvShowDetail.hide()
                    layoutDataDetailTvShow.visible()
                    it.data?.let { tvShowDetail -> observeTvShowDetail(tvShowDetail) }
                }
                -1 -> {
                    observeError(it.error)
                }
            }
        })
    }

    private fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }
    }

    private fun observeTvShowDetail(tvShow: TvShowDetailResponse) {
        var genres: String = ""
        if (tvShow.genres != null) {
            for (genre in tvShow.genres) {
                genres += genre?.name.toString() + ", "
            }
        }
        binding.tvDetail = tvShow
        binding.imageBackdropTV = tvShow.backdropPath
        binding.tvGenres = genres
        binding.imageCirclePosterTV = tvShow.posterPath
        binding.rating = tvShow.voteAverage?.div(2)
    }
}
