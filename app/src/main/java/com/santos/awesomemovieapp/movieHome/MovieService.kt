package com.santos.awesomemovieapp.movieHome

import com.santos.awesomemovieapp.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("now_playing")
    fun getMoviesList(
        @Query("page") page: String,
        @Query("api_key") apikey: String
    ): Call<MovieResponse>

    @GET("now_playing")
    fun getMovieItem(
        @Query("api_key") apikey: String,
        @Query("movie_id") movieId: String
    )

    fun getMovieImage(
        @Query("movie_id/images") apikey: String
    )
}