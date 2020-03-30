package com.example.android.databinding.basicsample.data.remote.source

import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable

interface RemoteDataSource {

    fun getMovieDataFromApi(apiKey: String): Observable<List<MovieEntity>>

    fun getMovieDataDetailFromApi(apiKey: String, id: String): Observable<MovieDetailEntity>

    fun getTvShowDataFromAPI(apiKey: String): Observable<List<TvShowEntity>>

    fun getTvShowDataDetailFromApi(apiKey: String, id: String): Observable<TvShowDetailEntity>
}