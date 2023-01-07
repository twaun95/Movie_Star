package com.twaun95.moviestar.data.repository

import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.local.dao.BookMarkMovieDao
import com.twaun95.moviestar.data.local.entity.BookMarkMovieEntity
import com.twaun95.moviestar.domain.model.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarkMovieRepository  @Inject constructor(
    private val movieDao: BookMarkMovieDao
) {
    fun getMovies() : Flow<List<MovieEntity>> {
        return movieDao.getMovies().map {
            it.map {  entity->
                BookMarkMovieEntity.toMovieEntity(entity)
            }
        }
    }

    suspend fun addMovie(movie: BookMarkMovieEntity) {
        Logger.d("BookMarkMovieRepository $movie")
        movieDao.addBookMarkMovie(movie)
    }

    suspend fun removeMovie(id: String) {
        movieDao.removeMovie(id)
    }
}