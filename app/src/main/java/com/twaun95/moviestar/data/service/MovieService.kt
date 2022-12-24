package com.twaun95.moviestar.data.service

import com.twaun95.moviestar.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET
    suspend fun getMovieWithSearch(
        @Query("apiKey") apiKey: String,
        @Query("s") search: String,
        @Query("page") page: String? = null
    ) : Response<MovieResponse>
}