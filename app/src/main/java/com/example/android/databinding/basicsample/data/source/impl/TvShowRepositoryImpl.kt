package com.example.android.databinding.basicsample.data.source.impl

import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.source.TvShowRepository
import io.reactivex.Observable


class TvShowRepositoryImpl(private val remoteImpl: RemoteDataSourceImpl,
                           private val localImpl: LocalDataSourceImpl) : TvShowRepository {

    override fun getTvShowData(apiKey: String): Observable<List<TvShowEntity>> =
            Observable.concatArrayEager(localImpl.getTvShowData(), remoteImpl.getTvShowDataFromAPI(apiKey))


    override fun favoriteTvShows(tvShow: TvShowDetailEntity) {
        TODO("Not yet implemented")
    }

    override fun getTvShowDetail(apiKey: String, id: String): Observable<TvShowDetailEntity> =
            Observable.concatArrayEager(localImpl.getTvShowDetail(id), remoteImpl.getTvShowDataDetailFromApi(apiKey, id))

    override fun updateTvShowDetail(isFavorite: Boolean, tvShow: TvShowDetailEntity) {
        if (isFavorite) {
            localImpl.updateTvShowDetail(false, tvShow)
        } else {
            localImpl.updateTvShowDetail(true, tvShow)
        }

    }
}