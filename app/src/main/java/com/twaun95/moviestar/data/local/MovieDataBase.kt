package com.twaun95.moviestar.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twaun95.moviestar.data.local.dao.BookMarkMovieDao
import com.twaun95.moviestar.data.local.entity.BookMarkMovieEntity


@Database(entities = [BookMarkMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun movieDao(): BookMarkMovieDao
}