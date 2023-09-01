package com.santos.awesomemovieapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    val dates: DateRange,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
