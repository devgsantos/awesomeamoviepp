package com.santos.awesomemovieapp.data

import com.squareup.moshi.Json

data class SpokenLanguage(
    @Json(name = "english_name") var englishName : String? = null,
    @Json(name = "iso_639_1") var iso6391     : String? = null,
    @Json(name="name"         ) var name        : String? = null
)
