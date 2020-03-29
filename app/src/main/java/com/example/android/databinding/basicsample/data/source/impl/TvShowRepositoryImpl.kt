package com.example.android.databinding.basicsample.data.source.impl

import com.example.android.databinding.basicsample.data.local.LocalDataSource
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.remote.RemoteDataSource
import com.example.android.databinding.basicsample.data.source.TvShowRepository
import io.reactivex.Observable


class TvShowRepositoryImpl(private val remote: RemoteDataSource,
                           private val local: LocalDataSource) : TvShowRepository {

    override fun getTvShowData(apiKey: String): Observable<List<TvShowEntity>> =
            Observable.concatArrayEager(local.getTvShowData(), remote.getTvShowDataFromAPI(apiKey))


    override fun favoriteTvShows(tvShow: TvShowDetailEntity) {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetail(apiKey: String, id: String): Observable<TvShowDetailEntity> =
            Observable.concatArrayEager(local.getTvShowDetail(id), remote.getTvShowDataDetailFromApi(apiKey, id))
}