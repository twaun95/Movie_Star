package com.twaun95.moviestar.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


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
)