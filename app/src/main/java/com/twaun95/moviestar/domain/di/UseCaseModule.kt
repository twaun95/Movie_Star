package com.twaun95.moviestar.domain.di

import android.graphics.Movie
import com.twaun95.moviestar.domain.repository.MovieRepository
import com.twaun95.moviestar.domain.usecase.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesGetBoxOfficeUseCase(repository: MovieRepository): MovieUseCase {
        return MovieUseCase(repository)
    }
}