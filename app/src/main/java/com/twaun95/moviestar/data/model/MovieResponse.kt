package com.twaun95.moviestar.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    val Search: List<Search>,
    @SerializedName("totalResults")
    val totalResults: String,
    @SerializedName("Response")
    val Response: String
)