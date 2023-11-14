package com.santos.awesomemovieapp

import com.google.common.truth.Truth.assertThat
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.datasource.MovieDataBaseDataSource
import com.santos.awesomemovieapp.datasource.MovieDataSource
import com.santos.awesomemovieapp.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    var movieApiClientDataSource: MovieDataSource = mockk()
    var movieDataBaseDataSource: MovieDataBaseDataSource = mockk()

    val repository = MovieRepository(movieApiClientDataSource, movieDataBaseDataSource)
    val movieList = listOf(Movie())

    @Before
    fun setup() {
        coEvery {
            movieDataBaseDataSource.clearMovieData()
        } returns Unit

        coEvery {
            movieDataBaseDataSource.saveMovieData(any())
        } returns Unit
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getData_whenApiSourceHadSuccess_shouldPersistDataAndReturnList() = runTest{
        // Preparação
        val apiResponse = Result.success((movieList))
        coEvery {
            movieApiClientDataSource.getMovieData()
        } returns apiResponse

        // Execução
        val result = repository.getMovieData()

        // Validação
        assertThat(result).isEqualTo(apiResponse)
        coVerifySequence {
            movieApiClientDataSource.getMovieData()
            movieDataBaseDataSource.clearMovieData()
            movieDataBaseDataSource.saveMovieData(movieList)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getData_whenApiSourceFails_shouldLoadLocalData() = runTest {
        // Preparação
        val apiResponse = Result.failure<List<Movie>>(Throwable("Teste"))
        val dbResponse = Result.success((movieList))
        coEvery {
            movieApiClientDataSource.getMovieData()
        } returns apiResponse

        coEvery {
            movieDataBaseDataSource.getMovieData()
        } returns dbResponse

        // Execução
        val result = repository.getMovieData()

        // Validação
        coVerify(exactly = 0) {
            movieDataBaseDataSource.clearMovieData()
            movieDataBaseDataSource.saveMovieData(any())
        }

        coVerify(exactly = 1) {
            movieDataBaseDataSource.getMovieData()
        }

        assertThat(result).isEqualTo(dbResponse)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getData_whenApiSourceThrowsExcepetion_ShouldReturnLocalData() = runTest {

        // Preparação
        val dbResponse = Result.success((movieList))

        coEvery {
            movieApiClientDataSource.getMovieData()
        }.throws(Exception("Teste"))

        coEvery {
            movieDataBaseDataSource.getMovieData()
        } returns dbResponse

        // Execução
        val result = repository.getMovieData()

        coVerify(exactly = 0) {
            movieDataBaseDataSource.clearMovieData()
            movieDataBaseDataSource.saveMovieData(any())
        }

        coVerify(exactly = 1) {
            movieDataBaseDataSource.getMovieData()
        }

        assertThat(result).isEqualTo(dbResponse)
    }
}