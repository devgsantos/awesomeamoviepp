package com.santos.awesomemovieapp.datasource

import android.content.Context
import com.santos.awesomemovieapp.dao.MovieDao
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.MovieWithAllProperties
import com.santos.awesomemovieapp.database.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDataBaseDataSource @Inject constructor(): MovieDataSource {

    @Inject
    lateinit var movieDao: MovieDao

    override suspend fun getMovieData(): Result<List<Movie>?> =
        withContext(Dispatchers.IO) {
            Result.success(loadPersistedMovieData())
        }

    override suspend fun saveMovieData(movieList: List<Movie>) {
        movieDao.insertMovieList(movieList)
    }

    override suspend fun clearMovieData() {
        movieDao.clearMovieData()
    }


    private suspend fun loadPersistedMovieData() = movieDao.getAllMovies()?.map {
        mapMovieWithPropertiesToMovie(it)
    }

    private fun mapMovieWithPropertiesToMovie(movieWithAllProperties: MovieWithAllProperties): Movie {
        movieWithAllProperties.movie.genres = movieWithAllProperties.genres
        movieWithAllProperties.movie.productionCompanies = movieWithAllProperties.productionCompany
        movieWithAllProperties.movie.productionCountries = movieWithAllProperties.productionCountry
        movieWithAllProperties.movie.spokenLanguages = movieWithAllProperties.spokenLanguage
        return movieWithAllProperties.movie
    }
}