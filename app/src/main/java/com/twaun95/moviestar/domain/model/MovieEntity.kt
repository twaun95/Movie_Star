package com.twaun95.moviestar.domain.model

import com.google.gson.annotations.SerializedName

data class MovieEntity(
    val title: String,
    val year: String,
    val imdbID: String,
    val type: String,
    val poster: String
)
