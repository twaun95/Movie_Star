package com.twaun95.moviestar.domain.usecase

import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.model.Result
import com.twaun95.moviestar.domain.repository.MovieRepository
import javax.inject.Inject

class NextPageUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(
        search: String
    ): Result<List<MovieEntity>> {
        return movieRepository.getNextPage(search).also {
            Logger.d("usecase $it")
        }
    }
}