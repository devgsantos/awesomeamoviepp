package com.santos.awesomemovieapp.data

import com.squareup.moshi.Json

data class ProductionCountry(
    @Json(name="iso_3166_1" ) var iso31661 : String? = null,
    @Json(name="name"       ) var name     : String? = null
)
