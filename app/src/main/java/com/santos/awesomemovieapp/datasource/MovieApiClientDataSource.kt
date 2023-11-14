package com.santos.awesomemovieapp.datasource

import com.santos.awesomemovieapp.api.MovieService
import com.santos.awesomemovieapp.data.ApiCredentials
import com.santos.awesomemovieapp.data.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class MovieApiClientDataSource @Inject constructor(): MovieDataSource {

    @Inject
    lateinit var movieService: MovieService

    override suspend fun getMovieData(): Result<List<Movie>?> =
        withContext(Dispatchers.IO) {
            val page = 1
            val response = movieService.getMoviesList(page.toString(), ApiCredentials.apiKey)
            when {
                response.isSuccessful -> Result.success(response.body()?.results)
                else -> Result.failure(Throwable(response.message()))
            }
        }

    override suspend fun saveMovieData(movieList: List<Movie>) {
        TODO("Not yet implemented")
    }

    override suspend fun clearMovieData() {
        TODO("Not yet implemented")
    }
}