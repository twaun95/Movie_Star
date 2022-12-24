package com.twaun95.moviestar.domain.usecase

import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(
        search: String,
        page: Int
    ): List<MovieEntity> {
        return movieRepository.getSearchList(search, page)
    }
}