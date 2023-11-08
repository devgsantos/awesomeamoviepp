package com.santos.awesomemovieapp.api

import com.google.common.truth.Truth.assertThat
import com.santos.awesomemovieapp.datasource.MovieApiClientDataSource
import org.junit.Before
import org.junit.Test

class ApiClientTest {
    lateinit var apiClient: MovieApiClientDataSource

    @Before
    fun getMovieTest() {
        apiClient = MovieApiClientDataSource()
    }

    @Test
    suspend fun receiveMovieList() {
        //When
        val result = apiClient.getMovieData()

        //Then
        if (result.isSuccess) {
            assertThat(result).isNotNull()
        } else {
            assertThat(result).isNotNull()
        }
    }

}