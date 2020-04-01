package com.example.android.databinding.basicsample.data.source

import androidx.paging.PagedList
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable
import io.reactivex.Single

interface TvShowRepository {

    fun getTvShowData(apiKey: String): Observable<List<TvShowEntity>>

    fun getTvShowDetail(apiKey: String, id: String): Observable<TvShowDetailEntity>

    fun updateTvShowDetail(isFavorite: Boolean, tvShow: TvShowDetailEntity): Single<Int>

    fun getAllFavoriteTvShow(isFavorite: Boolean): Observable<PagedList<TvShowDetailEntity>>
}