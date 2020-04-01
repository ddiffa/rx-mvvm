package com.example.android.databinding.basicsample.data.source.impl

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.source.LocalDataSourceImpl
import com.example.android.databinding.basicsample.data.remote.source.RemoteDataSourceImpl
import com.example.android.databinding.basicsample.data.source.MovieRepository
import io.reactivex.Observable
import io.reactivex.Single


class MovieRepositoryImpl(
        private val remoteImpl: RemoteDataSourceImpl,
        private val localImpl: LocalDataSourceImpl
) : MovieRepository {

    override fun getMovieData(apiKey: String): Observable<List<MovieEntity>> {
        return Observable.concatArrayEager(localImpl.getAllMovieData(), remoteImpl.getMovieDataFromApi(apiKey))
    }

    override fun getMovieDataDetail(apiKey: String, id: String): Observable<MovieDetailEntity> =
            Observable.concatArrayEager(localImpl.getMovieDetail(id), remoteImpl.getMovieDataDetailFromApi(apiKey, id))

    override fun updateMovieDetail(isFavorite: Boolean, movie: MovieDetailEntity): Single<Int> {
        return if (isFavorite) {
            localImpl.updateMovieDetail(false, movie)
        } else {
            localImpl.updateMovieDetail(true, movie)
        }

    }

    override fun getAllFavoriteMovie(isFavorite: Boolean): Observable<PagedList<MovieDetailEntity>> =
            RxPagedListBuilder(localImpl.getAllFavoriteMovie(isFavorite), 20).buildObservable()

}