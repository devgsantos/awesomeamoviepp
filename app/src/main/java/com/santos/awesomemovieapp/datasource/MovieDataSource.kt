package com.santos.awesomemovieapp.datasource

import com.santos.awesomemovieapp.data.Movie

interface MovieDataSource {
    suspend fun getMovieData(): Result<List<Movie>?>
    suspend fun saveMovieData(movieList: List<Movie>)

    suspend fun clearMovieData()
}