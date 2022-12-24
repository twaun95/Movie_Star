package com.twaun95.moviestar.data.di

import com.twaun95.moviestar.data.repository.MovieRepositoryImpl
import com.twaun95.moviestar.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesMovieRepository(repositoryImpl: MovieRepositoryImpl): MovieRepository {
        return repositoryImpl
    }
}