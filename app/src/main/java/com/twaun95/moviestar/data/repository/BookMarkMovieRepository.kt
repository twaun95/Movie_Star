package com.twaun95.moviestar.data.repository

import com.twaun95.moviestar.application.Logger
import com.twaun95.moviestar.data.local.dao.BookMarkMovieDao
import com.twaun95.moviestar.data.local.entity.BookMarkMovieEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarkMovieRepository  @Inject constructor(
    private val movieDao: BookMarkMovieDao
) {
    fun getMovies() : Flow<List<BookMarkMovieEntity>> {
        return movieDao.getMovies().also {
            Logger.d("BookMarkMovieRepository $it")
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