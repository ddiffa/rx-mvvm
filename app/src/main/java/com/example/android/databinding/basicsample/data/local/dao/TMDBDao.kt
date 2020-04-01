package com.example.android.databinding.basicsample.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TMDBDao {

    @Query("SELECT * FROM now_playingdb")
    fun getNowPlayingMovie(): Observable<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie( movie: List<MovieEntity>): Completable

    @Query("SELECT * FROM tvshowdb")
    fun getAllTvShowData(): Observable<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow( tvShows: List<TvShowEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieDetail(movieDetailEntity: MovieDetailEntity) : Completable

    @Query("SELECT * FROM moviedetail_db where id = :id")
    fun getMovieDetail(id: String): Observable<MovieDetailEntity>

    @Query("SELECt * FROM tvshowdetail_db where id = :id")
    fun getTvShowDetail(id: String): Observable<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTvShowDetail(tvShowDetailEntity: TvShowDetailEntity) : Completable

    @Update
    fun updateMovieDetail(movie: MovieDetailEntity): Single<Int>

    @Update
    fun updateTvShowDetail(tvShow: TvShowDetailEntity): Single<Int>

    @Query("SELECT * FROM moviedetail_db where isFavorite = :isFavorite")
    fun getAllMovieFavorite(isFavorite: Boolean): DataSource.Factory<Int, MovieDetailEntity>

    @Query("SELECT * FROM tvshowdetail_db where isFavorite = :isFavorite")
    fun getAllTvShowFavorite(isFavorite: Boolean): DataSource.Factory<Int, TvShowDetailEntity>
}