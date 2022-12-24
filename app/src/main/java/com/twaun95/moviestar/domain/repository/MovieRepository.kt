package com.twaun95.moviestar.domain.repository

import com.twaun95.moviestar.domain.model.MovieEntity

interface MovieRepository {
    suspend fun getSearchList(search: String, page: Int) : List<MovieEntity>
}