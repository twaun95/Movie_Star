package com.twaun95.moviestar.domain.model

data class MovieEntity(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String,
    val isBookMarked: Boolean? = false
)
