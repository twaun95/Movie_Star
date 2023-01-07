package com.twaun95.moviestar.data.di

import android.content.Context
import androidx.room.Room
import com.twaun95.moviestar.data.local.MovieDataBase
import com.twaun95.moviestar.data.local.dao.BookMarkMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideBookMarkMovieDao(dataBase: MovieDataBase): BookMarkMovieDao {
        return dataBase.movieDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MovieDataBase{
        return Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            "MOVIE_DB")
            .build()
    }
}