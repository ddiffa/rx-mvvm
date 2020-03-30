package com.example.android.databinding.basicsample.ui.feature.detailtvshow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.remote.response.error.ApiError
import com.example.android.databinding.basicsample.databinding.ActivityDetailTvshowBinding
import com.example.android.databinding.basicsample.ui.viewstate.ViewState
import com.example.android.databinding.basicsample.utils.hide
import com.example.android.databinding.basicsample.utils.loggingError
import com.example.android.databinding.basicsample.utils.visible
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.imgBack
import kotlinx.android.synthetic.main.activity_detail_tvshow.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailTVShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvshowBinding

    private val viewModel by viewModel<DetailTvShowViewModel>()
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
                ViewState.State.LOADING -> {
                    layoutDataDetailTvShow.hide()
                    shimmerTvShowDetail.visible()
                }
                ViewState.State.SUCCESS -> {
                    shimmerTvShowDetail.stopShimmerAnimation()
                    shimmerTvShowDetail.hide()
                    layoutDataDetailTvShow.visible()
                    it.data?.let { tvShowDetail -> observeTvShowDetail(tvShowDetail) }
                }
                ViewState.State.FAILED -> {
                    it.err?.let { err -> observeError(err) }
                }
            }
        })
    }

    private fun observeError(err: ApiError) {
        loggingError(DetailTVShowActivity::class.java.simpleName, err.logMessage)
        Toast.makeText(applicationContext, err.message, Toast.LENGTH_SHORT).show()
    }

    private fun observeTvShowDetail(tvShow: TvShowDetailEntity?) {
        var genres: String = ""
        for (genre in tvShow?.genres!!) {
            genres += genre?.name.toString() + ", "
        }
        if (tvShow.isFavorite!!) {
            fbFavoriteTvShow.setImageResource(R.drawable.heart)
        } else {
            fbFavoriteTvShow.setImageResource(R.drawable.heart_gray)
        }
        binding.tvDetail = tvShow
        binding.imageBackdropTV = tvShow.backdropPath
        binding.tvGenres = genres
        binding.imageCirclePosterTV = tvShow.posterPath
        binding.rating = tvShow.voteAverage?.div(2)
        binding.handler = this
    }

    fun onFavoriteClick(isFavorite: Boolean, tvShow: TvShowDetailEntity) {
        viewModel.updateTvShowDetail(isFavorite, tvShow)
        if (isFavorite) {
            Toast.makeText(applicationContext, "Tv Show has been deteled from favorite", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "Tv Show has been added to favorite", Toast.LENGTH_SHORT).show()
        }
    }
}
