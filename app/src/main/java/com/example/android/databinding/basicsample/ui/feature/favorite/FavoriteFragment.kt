package com.example.android.databinding.basicsample.ui.feature.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.databinding.basicsample.R
import com.example.android.databinding.basicsample.ui.feature.favorite.movie.MovieFavoriteFragment
import com.example.android.databinding.basicsample.ui.feature.favorite.tvshow.TvShowFavoriteFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    val movieFavorite by inject<MovieFavoriteFragment>()
    val tvShowFavorite by inject<TvShowFavoriteFragment>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter = PagerAdapter(childFragmentManager)
        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)
        tabLayout.getTabAt(0)?.text = "Movie"
        tabLayout.getTabAt(1)?.text = "Tv Show"
    }

    inner class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment =
                when (position) {
                    0 -> movieFavorite
                    1 -> tvShowFavorite
                    else -> movieFavorite
                }

        override fun getCount(): Int = 2

    }

}
