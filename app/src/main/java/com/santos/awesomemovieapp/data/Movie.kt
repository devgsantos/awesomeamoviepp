package com.santos.awesomemovieapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@JsonClass(generateAdapter = true)
data class Movie(
    @Json(name="adult"                 ) var adult               : Boolean?                       = null,
    @Json(name="backdrop_path"         ) var backdropPath        : String?                        = null,
    @Json(name="belongs_to_collection" ) var belongsToCollection : BelongsToCollection?           = BelongsToCollection(),
    @Json(name="budget"                ) var budget              : Int?                           = null,
    @Json(name="genres"                ) var genres              : List<Genre>              = arrayListOf(),
    @Json(name="homepage"              ) var homepage            : String?                        = null,
    @Json(name="id"                    ) var id                  : Int?                           = null,
    @Json(name="imdb_id"               ) var imdbId              : String?                        = null,
    @Json(name="original_language"     ) var originalLanguage    : String?                        = null,
    @Json(name="original_title"        ) var originalTitle       : String?                        = null,
    @Json(name="overview"              ) var overview            : String?                        = null,
    @Json(name="popularity"            ) var popularity          : Double?                        = null,
    @Json(name="poster_path"           ) var posterPath          : String?                        = null,
    @Json(name="production_companies"  ) var productionCompanies : List<ProductionCompany> = arrayListOf(),
    @Json(name="production_countries"  ) var productionCountries : List<ProductionCountry> = arrayListOf(),
    @Json(name="release_date"          ) var releaseDate         : String?                        = null,
    @Json(name="revenue"               ) var revenue             : Int?                           = null,
    @Json(name="runtime"               ) var runtime             : Int?                           = null,
    @Json(name="spoken_languages"      ) var spokenLanguages     : List<SpokenLanguage>     = arrayListOf(),
    @Json(name="status"                ) var status              : String?                        = null,
    @Json(name="tagline"               ) var tagline             : String?                        = null,
    @Json(name="title"                 ) var title               : String?                        = null,
    @Json(name="video"                 ) var video               : Boolean?                       = null,
    @Json(name="vote_average"          ) var voteAverage         : Double?                        = null,
    @Json(name="vote_count"            ) var voteCount           : Int?                           = null
) {

    val imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    fun getContent(): String {
        return when {
            title?.isNotEmpty() == true -> title!!
            overview?.isNotEmpty() == true -> overview!!
            else -> "Conteúdo não disponível."
        }
    }

    fun getIdString(): String {
        return id.toString()
    }

    fun getPosterUrl(): String {
        return "$imageBaseUrl$posterPath"
    }

    fun getImageUrl(): String {
        return when {
            posterPath?.isNotEmpty() == true -> "https://image.tmdb.org/t/p/original/" + posterPath!!
            else -> ""
        }
    }

    val list = listOf("https://image.tmdb.org/t/p/original/" + backdropPath, "https://image.tmdb.org/t/p/original/" + posterPath)
    fun getCarouselImages() = list.map {
        CarouselItem(imageUrl = it)
    }

}
