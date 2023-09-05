package com.santos.awesomemovieapp.data

import com.squareup.moshi.Json

data class BelongsToCollection(
    @Json(name = "id") var id           : Int?    = null,
    @Json(name = "name") var name         : String? = null,
    @Json(name = "poster_path") var posterPath   : String? = null,
    @Json(name = "backdrop_path") var backdropPath : String? = null
)
