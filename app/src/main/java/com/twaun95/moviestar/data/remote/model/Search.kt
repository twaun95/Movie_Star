package com.twaun95.moviestar.data.remote.model

import com.google.gson.annotations.SerializedName
import com.twaun95.moviestar.domain.model.MovieEntity

data class Search(
    @SerializedName("Title")
    val Title: String,
    @SerializedName("Year")
    val Year: String,
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Type")
    val Type: String,
    @SerializedName("Poster")
    val Poster: String
) {
    companion object {
        fun toMovieEntity(search: Search) : MovieEntity {
            return MovieEntity(
                title = search.Title,
                year = search.Year,
                imdbID = search.imdbID,
                type = search.Type,
                poster = search.Poster
            )
        }
    }
}