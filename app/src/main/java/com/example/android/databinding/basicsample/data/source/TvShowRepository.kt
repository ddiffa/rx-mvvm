package com.example.android.databinding.basicsample.data.source

import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable

interface TvShowRepository {

    fun getTvShowData(apiKey: String): Observable<List<TvShowEntity>>

    fun favoriteTvShows(tvShow: TvShowDetailEntity)

    fun getTvShowDetail(apiKey: String, id: String) : Observable<TvShowDetailEntity>
}