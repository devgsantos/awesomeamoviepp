package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.MovieWithAllProperties
import com.santos.awesomemovieapp.data.SpokenLanguage
import com.santos.awesomemovieapp.database.MovieDatabase
import javax.inject.Inject

@Dao
abstract class MovieDao(
    private var movieDatabase: MovieDatabase
): BaseDao<Movie> {
    @Inject
    lateinit var genreDao: GenreDao

    @Inject
    lateinit var productionCompanyDao: ProductionCompanyDao

    @Inject
    lateinit var productionCountryDao: ProductionCountryDao

    @Inject
    lateinit var spokenLanguageDao: SpokenLanguageDao

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