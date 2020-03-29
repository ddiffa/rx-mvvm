package com.example.android.databinding.basicsample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.databinding.basicsample.data.local.dao.TMDBDao
import com.example.android.databinding.basicsample.data.local.entity.MovieDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.MovieEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowDetailEntity
import com.example.android.databinding.basicsample.data.local.entity.TvShowEntity
import com.example.android.databinding.basicsample.utils.Converter
import com.example.android.databinding.basicsample.utils.GenresConverter

@Database(entities = [MovieEntity::class, TvShowEntity::class, MovieDetailEntity::class, TvShowDetailEntity::class], version = 1)
@TypeConverters(Converter::class, GenresConverter::class)
abstract class TMDBdb : RoomDatabase() {
    abstract fun tmdbDao(): TMDBDao
}