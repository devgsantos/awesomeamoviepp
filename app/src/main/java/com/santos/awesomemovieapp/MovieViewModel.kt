package com.santos.awesomemovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.awesomemovieapp.api.MovieService
import com.santos.awesomemovieapp.data.ApiCredentials
import com.santos.awesomemovieapp.data.DataState
import com.santos.awesomemovieapp.data.Event
import com.santos.awesomemovieapp.data.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel: ViewModel() {

    val moviewDetailsLiveData: LiveData<Movie>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<Movie>()

    val moviewListLiveData: LiveData<List<Movie>?>
        get() = _movieListLiveData
    private val _movieListLiveData = MutableLiveData<List<Movie>>()

    val navigateToDetailsLiveData
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Event<Unit>>()

    val dataStateLiveData: LiveData<DataState>
        get() = _dataStateLiveData
    private val _dataStateLiveData = MutableLiveData<DataState>()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiCredentials.baseApiUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val movieService = retrofit.create(MovieService::class.java)


    init {
        _dataStateLiveData.postValue(DataState.LOADING)
//      _moviewListLiveData.postValue(PlaceholderContent.ITEMS)
        getMovieData()
    }

    fun getMovieData() {
        val page = 1

        viewModelScope.launch {
            val response = movieService.getMoviesList(page.toString(), ApiCredentials.apiKey)
            if (response.isSuccessful) {
                _movieListLiveData.postValue(response.body()?.results)
                _dataStateLiveData.postValue(DataState.SUCCESS)
            } else {
                _dataStateLiveData.postValue(DataState.ERROR)
            }
        }

    }

    fun onMovieSelected(position: Int) {
        val movieDetails = _movieListLiveData.value?.get(position)
        movieDetails?.let {
            _movieDetailsLiveData.postValue(it)
            _navigateToDetailsLiveData.postValue(Event(Unit))
        }
    }
}