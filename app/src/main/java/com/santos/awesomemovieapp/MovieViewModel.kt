package com.santos.awesomemovieapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.santos.awesomemovieapp.api.MovieService
import com.santos.awesomemovieapp.data.ApiCredentials
import com.santos.awesomemovieapp.data.DataState
import com.santos.awesomemovieapp.data.Event
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.database.MovieDatabase
import com.santos.awesomemovieapp.repository.MovieRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel( application: Application): AndroidViewModel(application) {

    val moviewDetailsLiveData: LiveData<Movie>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie>()

    val movieListLiveData: LiveData<List<Movie>?>
        get() = _movieListLiveData
    private val _movieListLiveData = MutableLiveData<List<Movie>?>()

    val navigateToDetailsLiveData
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Event<Unit>>()

    val dataStateLiveData: LiveData<DataState>
        get() = _dataStateLiveData
    private val _dataStateLiveData = MutableLiveData<DataState>()


    private val movieRepository = MovieRepository(application)

    init {
        _dataStateLiveData.postValue(DataState.LOADING)
//      _moviewListLiveData.postValue(PlaceholderContent.ITEMS)
        CoroutineScope(Dispatchers.IO).launch {
            getMovieData()
        }
    }

    fun onMovieSelected(position: Int) {
        val movieDetails = _movieListLiveData.value?.get(position)
        movieDetails?.let {
            _movieDetailsLiveData.postValue(it)
            _navigateToDetailsLiveData.postValue(Event(Unit))
        }
    }

    fun getMovieData() {
        _dataStateLiveData.postValue(DataState.LOADING)
        viewModelScope.launch {
            val movieListResult = movieRepository.getMovieData()
            movieListResult.fold(
                onSuccess = {
                    _movieListLiveData.value = it
                    _dataStateLiveData.postValue(DataState.SUCCESS)
                },
                onFailure = {
                    _dataStateLiveData.postValue(DataState.ERROR)
                }
            )

        }
    }

// REFACTOR REPOSITORY

//    suspend fun getMovieData() {
//        val page = 1
//        try {
//            viewModelScope.launch {
//                val response = movieService.getMoviesList(page.toString(), ApiCredentials.apiKey)
//                if (response.isSuccessful) {
//                    val movies = response.body()?.results
//
//                    movies?.let {
//                        persistMovieData(it)
//                    }
//
//                    _movieListLiveData.postValue(movies)
//                    _dataStateLiveData.postValue(DataState.SUCCESS)
//                } else {
//                    errorHandling()
//                }
//            }
//        } catch (e: Exception) {
//            errorHandling()
//        }
//    }
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
//    private suspend fun persistMovieData(movieList: List<Movie>) {
//        movieDao.clearMovieData()
//        movieDao.insertMovieList(movieList)
//    }
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