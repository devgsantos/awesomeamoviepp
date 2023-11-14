package com.santos.awesomemovieapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.santos.awesomemovieapp.data.DataState
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.justRun
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
//    val dispatcher = TestCoroutineDispatcher()
    val dispatcher = StandardTestDispatcher()

    val movieRepository: MovieRepository = mockk()
    val appStateObserver: Observer<DataState> = mockk(relaxed = true)
    val appStateValues = mutableListOf<DataState>()

    lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxed = true)
        justRun {
            appStateObserver.onChanged(capture(appStateValues))
        }
        coEvery {
            movieRepository.getMovieData()
        } returns Result.failure(Throwable("Teste"))
        movieViewModel = MovieViewModel(movieRepository)
        movieViewModel.dataStateLiveData.observeForever(appStateObserver)
        appStateValues.clear()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        movieViewModel.dataStateLiveData.removeObserver(appStateObserver)
        appStateValues.clear()
    }

    @Test
    fun getMovieData_whenMovieRepository_hasData_shouldChangeToSuccess() = runTest {
        coEvery {
            movieRepository.getMovieData()
        } returns Result.success(listOf(Movie()))
        movieViewModel.getMovieData()
        assertThat(appStateValues).isEqualTo(listOf(DataState.LOADING, DataState.SUCCESS))
    }

    @Test
    fun getMovieData_whenMovieRepository_hasData_shouldChangeToError() = runTest {
        coEvery {
            movieRepository.getMovieData()
        } returns Result.failure(Throwable("Teste"))
        movieViewModel.getMovieData()
        assertThat(appStateValues).isEqualTo(listOf(DataState.LOADING, DataState.ERROR))
    }

    @Test
    fun getMovieData_whenMovieRepository_hasData_shouldEmitList() = runTest {
        val list = listOf(Movie())
        coEvery {
            movieRepository.getMovieData()
        } returns Result.success(listOf(Movie()))
        movieViewModel.getMovieData()
        assertThat(movieViewModel.movieListLiveData.value).isEqualTo(list)
    }

    @Test
    fun getMovieData_whenMovieRepository_hasError_shouldNotEmitList() = runTest {
        coEvery {
            movieRepository.getMovieData()
        } returns Result.failure(Throwable("Teste"))
        movieViewModel.getMovieData()
        assertThat(appStateValues).isNull()
    }

}