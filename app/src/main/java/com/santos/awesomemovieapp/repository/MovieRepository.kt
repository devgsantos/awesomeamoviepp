package com.santos.awesomemovieapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.santos.awesomemovieapp.api.MovieService
import com.santos.awesomemovieapp.data.ApiCredentials
import com.santos.awesomemovieapp.data.DataState
import com.santos.awesomemovieapp.data.Event
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.MovieWithAllProperties
import com.santos.awesomemovieapp.database.MovieDatabase
import com.santos.awesomemovieapp.datasource.MovieApiClientDataSource
import com.santos.awesomemovieapp.datasource.MovieDataBaseDataSource
import com.santos.awesomemovieapp.datasource.MovieDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class MovieRepository @Inject constructor(
    var movieApiClientDataSource: MovieDataSource,
    var movieDataBaseDataSource: MovieDataBaseDataSource
) {
    suspend fun getMovieData(): Result<List<Movie>?> {
        return try {
            val result = movieApiClientDataSource.getMovieData()
            if (result.isSuccess) {
               persistData(result.getOrNull())
                result
            } else {
                getLocalData()
            }
        } catch (e: Exception) {
            getLocalData()
        }
    }

    private suspend fun persistData(movieList: List<Movie>?) {
        movieDataBaseDataSource.clearMovieData()
       movieList?.let {
            movieDataBaseDataSource.saveMovieData(it)
        }
    }

    private suspend fun getLocalData(): Result<List<Movie>?> = movieDataBaseDataSource.getMovieData()


//
//    val moviewDetailsLiveData: LiveData<Movie>
//        get() = _movieDetailsLiveData
//    private val _movieDetailsLiveData = MutableLiveData<Movie>()
//
//    val movieListLiveData: LiveData<List<Movie>?>
//        get() = _movieListLiveData
//    private val _movieListLiveData = MutableLiveData<List<Movie>?>()
//
//    val navigateToDetailsLiveData
//        get() = _navigateToDetailsLiveData
//    private val _navigateToDetailsLiveData = MutableLiveData<Event<Unit>>()
//
//    val dataStateLiveData: LiveData<DataState>
//        get() = _dataStateLiveData
//    private val _dataStateLiveData = MutableLiveData<DataState>()
//
//
//    private suspend fun errorHandling() {
//        val movieList = loadPersistedMovieData()
//        if (movieList.isNullOrEmpty()) {
//            _dataStateLiveData.postValue(DataState.ERROR)
//        } else {
//            _movieListLiveData.postValue(movieList)
//            _dataStateLiveData.postValue(DataState.SUCCESS)
//        }
//    }
//
//
//    private suspend fun loadPersistedMovieData() = movieDao.getAllMovies()?.map {
//        mapMovieWithPropertiesToMovie(it)
//    }
//
//    private fun mapMovieWithPropertiesToMovie(movieWithAllProperties: MovieWithAllProperties): Movie {
//        movieWithAllProperties.movie.genres = movieWithAllProperties.genres
//        movieWithAllProperties.movie.productionCompanies = movieWithAllProperties.productionCompany
//        movieWithAllProperties.movie.productionCountries = movieWithAllProperties.productionCountry
//        movieWithAllProperties.movie.spokenLanguages = movieWithAllProperties.spokenLanguage
//        return movieWithAllProperties.movie
//    }

}