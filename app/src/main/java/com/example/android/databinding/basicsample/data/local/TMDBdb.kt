package com.example.android.databinding.basicsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.databinding.basicsample.data.local.dao.TMDBDao
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailResponse
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.utils.Converter

@Database(entities = [MovieEntity::class, TvShowEntity::class, MovieDetailResponse::class], version = 1)
@TypeConverters(Converter::class)
abstract class TMDBdb : RoomDatabase() {
    abstract fun tmdbDao(): TMDBDao
}