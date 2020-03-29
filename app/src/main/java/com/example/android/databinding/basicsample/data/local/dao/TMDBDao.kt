package com.example.android.databinding.basicsample.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable

@Dao
interface TMDBDao {

    @Query("SELECT * FROM now_playingdb")
    fun getNowPlayingMovie(): Observable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(vararg movie: MovieEntity)

    @Query("SELECT * FROM tvshowdb")
    fun getAllTvShowData(): Observable<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(vararg tvShows: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieDetail(vararg movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM moviedetail_db where id = :id")
    fun getMovieDetail(id: String): Observable<MovieDetailEntity>

    @Query("SELECt * FROM tvshowdetail_db where id = :id")
    fun getTvShowDetail(id: String): Observable<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowDetail(vararg tvShowDetailEntity: TvShowDetailEntity)


}