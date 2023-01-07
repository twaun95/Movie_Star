package com.twaun95.moviestar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twaun95.moviestar.data.local.entity.BookMarkMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkMovieDao {
    @Query("SELECT * FROM movie ORDER BY id DESC")
    fun getMovies(): Flow<List<BookMarkMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookMarkMovie(movie: BookMarkMovieEntity)

    @Query("DELETE FROM movie WHERE imdbID = :movieId")
    suspend fun removeMovie(movieId: String)
}
