package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.MovieWithAllProperties
import com.santos.awesomemovieapp.database.MovieDatabase

@Dao
abstract class MovieDao(
    private val movieDatabase: MovieDatabase
): BaseDao<Movie> {

    private val genreDao =  movieDatabase.genreDao()
    private val productionCompanyDao = movieDatabase.productionCompanyDao()
    private val productionCountryDao =  movieDatabase.productionCountryDao()
    private val spokenLanguageDao = movieDatabase.spokenLanguageDao()

    @Transaction
    @Query( "SELECT * FROM movie")
    abstract suspend fun getAllMovies(): List<MovieWithAllProperties>?

    @Transaction
    @Query( "SELECT * FROM movie WHERE id = :id")
    abstract suspend fun getMovie(id: Int): MovieWithAllProperties?

    @Transaction
    @Query( "DELETE FROM movie")
    abstract suspend fun clearMovieData()

    @Transaction
    open suspend fun insertMovieList(movieList: List<Movie>) {
        movieList.forEach { insertMovie(it) }
    }

    @Transaction
    open suspend fun insertMovie(movie: Movie) {
        movie.genres?.forEach {
            it.movieId = movie.id
        }

        movie.productionCountries?.forEach {
            it.movieId = movie.id
        }

        movie.productionCountries?.forEach {
            it.movieId = movie.id
        }

        movie.spokenLanguages?.forEach {
            it.movieId = movie.id
        }

        insert(movie)
        movie.spokenLanguages?.let { spokenLanguageDao.insertList(it)}
        movie.genres?.let { genreDao.insertList(it)}
        movie.productionCountries?.let { productionCountryDao.insertList(it)}
        movie.productionCompanies?.let { productionCompanyDao.insertList(it)}
    }
}