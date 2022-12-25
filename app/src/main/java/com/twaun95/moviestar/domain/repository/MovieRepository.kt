package com.twaun95.moviestar.domain.repository

import com.twaun95.moviestar.domain.model.MovieEntity
import com.twaun95.moviestar.domain.model.Result

interface MovieRepository {
    suspend fun getSearchList(search: String) : Result<List<MovieEntity>>
    suspend fun getNextPage() : Result<List<MovieEntity>>
}