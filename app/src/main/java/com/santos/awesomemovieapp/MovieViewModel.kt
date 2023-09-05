package com.santos.awesomemovieapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santos.awesomemovieapp.MovieDetails.MovieDetails
import com.santos.awesomemovieapp.movieHome.MovieService
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.MovieResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieViewModel: ViewModel() {

    val moviewDetailsLiveData: LiveData<MovieDetails>
        get() = _movieDetailsLiveData
    private val _movieDetailsLiveData = MutableLiveData<MovieDetails>()

    val moviewListLiveData: LiveData<List<Movie>?>
        get() = _movieListLiveData
    private val _movieListLiveData = MutableLiveData<List<Movie>>()

    val navigateToDetailsLiveData
        get() = _navigateToDetailsLiveData
    private val _navigateToDetailsLiveData = MutableLiveData<Unit>()

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
        movieService.getMoviesList(page.toString(), ApiCredentials.apiKey)
            .enqueue(object: Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        _movieListLiveData.postValue(response.body()?.results)
                        _dataStateLiveData.postValue(DataState.SUCCESS)
                    } else {
                        _dataStateLiveData.postValue(DataState.ERROR)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                }

            })
    }

    fun onMovieSelected(position: Int) {
        val movieDetails = MovieDetails( "Nome do filme", "Mussum Ipsum, cacilds vidis litro abertis. Quem num gosta di mim que vai caçá sua turmis! Nullam volutpat risus nec leo commodo, ut interdum diam laoreet. Sed non consequat odio. Interagi no mé, cursus quis, vehicula ac nisi. Quem num gosta di mé, boa gentis num é.\n" +
                "\n" +
                "Copo furadis é disculpa de bebadis, arcu quam euismod magna. Delegadis gente finis, bibendum egestas augue arcu ut est. Praesent vel viverra nisi. Mauris aliquet nunc non turpis scelerisque, eget. Detraxit consequat et quo num tendi nada.",
            "2023")

        _movieDetailsLiveData.postValue(movieDetails)

        _navigateToDetailsLiveData.postValue(Unit)
    }
}