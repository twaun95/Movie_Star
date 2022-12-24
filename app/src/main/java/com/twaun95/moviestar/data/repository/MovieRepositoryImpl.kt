package com.twaun95.moviestar.data.repository

import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.model.APIKey
import com.twaun95.moviestar.data.model.Search
import com.twaun95.moviestar.data.service.MovieService
import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val apiKey: APIKey
) : MovieRepository {
    override suspend fun getSearchList(search: String, page: Int): List<MovieEntity> {
        val response = movieService.getMovieWithSearch(apiKey.key, search, page)

        Logger.d(response)

        return response.body()!!.Search.map {
            Search.toMovieEntity(it)
        }
    }
}