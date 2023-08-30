package com.santos.awesomemovieapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun getContent(): String {
        return when {
            title?.isNotEmpty() == true -> title
            overview?.isNotEmpty() == true -> overview
            else -> "Conteúdo não disponível."
        }
    }

    fun getIdString(): String {
        return id.toString() ?: ""
    }
}
