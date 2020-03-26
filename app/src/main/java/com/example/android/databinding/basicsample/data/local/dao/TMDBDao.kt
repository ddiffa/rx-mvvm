package com.example.android.databinding.basicsample.data.local.dao

import androidx.room.*
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TMDBDao {

    @Query("SELECT * FROM now_playingdb")
    fun getNowPlayingMovie(): Observable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM tvshowdb")
    fun getAllTvShowData(): Observable<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShows: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(movieDetailResponse: MovieDetailResponse)

    @Transaction
    @Query("SELECT * FROM moviedetail_db where id = :id")
    fun getMovieDetail(id: String): Single<MovieDetailResponse>


}