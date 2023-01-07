package com.twaun95.moviestar.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("Search")
    val Search: List<Search>? = emptyList(),
    @SerializedName("totalResults")
    val totalResults: String? = "0",
    @SerializedName("Response")
    val Response: String
)