package com.twaun95.moviestar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.twaun95.moviestar.data.remote.model.Search
import com.twaun95.moviestar.domain.model.MovieEntity


@Entity(tableName = "movie")
data class BookMarkMovieEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "year")
    var year: String,
    @ColumnInfo(name = "imdbID")
    var imdbID: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "typosterpe")
    var poster: String
){
    companion object {
        fun toMovieEntity(bookMarkMovie: BookMarkMovieEntity) : MovieEntity {
            return MovieEntity(
                title = bookMarkMovie.title,
                year = bookMarkMovie.year,
                imdbID = bookMarkMovie.imdbID,
                type = bookMarkMovie.type,
                poster = bookMarkMovie.poster,
                isBookMarked = true
            )
        }
    }
}