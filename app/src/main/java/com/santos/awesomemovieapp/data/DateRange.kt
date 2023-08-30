package com.santos.awesomemovieapp.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateRange(
    val maximum: String,
    val minimum: String
)
