package com.example.android.databinding.basicsample.data.local.dao

import androidx.room.*
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Observable
import io.reactivex.Single

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieDetail(vararg movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM moviedetail_db where id = :id")
    fun getMovieDetail(id: String): Observable<MovieDetailEntity>

    @Query("SELECt * FROM tvshowdetail_db where id = :id")
    fun getTvShowDetail(id: String): Observable<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShowDetail(vararg tvShowDetailEntity: TvShowDetailEntity)

    @Update
    fun updateMovieDetail(movie: MovieDetailEntity)

    @Update
    fun updateTvShowDetail(tvShow: TvShowDetailEntity)
//
//    @Query("SELECT * FROM moviedetail_db where isFavorite = :isFavorite")
//    fun getAllMovieFavorite(isFavorite: Boolean): DataSource.Factory<Int, Observable<List<MovieDetailEntity>>>
//
//    @Query("SELECT * FROM tvshowdetail_db where isFavorite = :isFavorite")
//    fun getAllTvShowFavorite(isFavorite: Boolean): DataSource.Factory<Int, Observable<List<TvShowDetailEntity>>>
}