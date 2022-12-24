package com.twaun95.moviestar.data.service

import com.twaun95.moviestar.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMovieWithSearch(
        @Query("apikey") apikey: String,
        @Query("s") s: String,
        @Query("page") page: Int? = null
    ) : Response<MovieResponse>
}