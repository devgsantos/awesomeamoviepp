package com.santos.awesomemovieapp.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@Entity
@JsonClass(generateAdapter = true)
class Movie() {

    @PrimaryKey
    @Json(name="id"                    ) var id                  : Int?                           = null
    @Json(name="adult"                 ) var adult               : Boolean?                       = null
    @Json(name="backdrop_path"         ) var backdropPath        : String?                        = null
    @Json(name="budget"                ) var budget              : Int?                           = null
    @Ignore
    @Json(name="genres"                ) var genres              : List<Genre>              = arrayListOf()
    @Json(name="homepage"              ) var homepage            : String?                        = null
    @Json(name="imdb_id"               ) var imdbId              : String?                        = null
    @Json(name="original_language"     ) var originalLanguage    : String?                        = null
    @Json(name="original_title"        ) var originalTitle       : String?                        = null
    @Json(name="overview"              ) var overview            : String?                        = null
    @Json(name="popularity"            ) var popularity          : Double?                        = null
    @Json(name="poster_path"           ) var posterPath          : String?                        = null
    @Ignore
    @Json(name="production_companies"  ) var productionCompanies : List<ProductionCompany> = arrayListOf()
    @Ignore
    @Json(name="production_countries"  ) var productionCountries : List<ProductionCountry> = arrayListOf()
    @Json(name="release_date"          ) var releaseDate         : String?                        = null
    @Json(name="revenue"               ) var revenue             : Int?                           = null
    @Json(name="runtime"               ) var runtime             : Int?                           = null
    @Ignore
    @Json(name="spoken_languages"      ) var spokenLanguages     : List<SpokenLanguage>     = arrayListOf()
    @Json(name="status"                ) var status              : String?                        = null
    @Json(name="tagline"               ) var tagline             : String?                        = null
    @Json(name="title"                 ) var title               : String?                        = null
    @Json(name="video"                 ) var video               : Boolean?                       = null
    @Json(name="vote_average"          ) var voteAverage         : Double?                        = null
    @Json(name="vote_count"            ) var voteCount           : Int?                           = null
    var imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    @Ignore
    constructor(
        id                  : Int?,
        adult               : Boolean?,
        backdropPath        : String?,
        budget              : Int?,
        genres              : List<Genre>,
        homepage            : String?,
        imdbId              : String?,
        originalLanguage    : String?,
        originalTitle       : String?,
        overview            : String?,
        popularity          : Double?,
        posterPath          : String?,
        productionCompanies : List<ProductionCompany>,
        productionCountries : List<ProductionCountry>,
        releaseDate         : String?,
        revenue             : Int?,
        runtime             : Int?,
        spokenLanguages     : List<SpokenLanguage>,
        status              : String?,
        tagline             : String?,
        title               : String?,
        video               : Boolean?,
        voteAverage         : Double?,
        voteCount           : Int?,
        imageBaseUrl: String?
    ): this() {
        this.id = id
        this.adult = adult
        this.backdropPath = backdropPath
        this.budget = budget
        this.genres = genres
        this.homepage = homepage
        this.imdbId = imdbId
        this.originalLanguage = originalLanguage
        this.originalTitle = originalTitle
        this.overview = overview
        this.popularity = popularity
        this.posterPath = posterPath
        this.productionCompanies = productionCompanies
        this.productionCountries = productionCountries
        this.releaseDate = releaseDate
        this.revenue = revenue
        this.runtime = runtime
        this.spokenLanguages = spokenLanguages
        this.status = status
        this.tagline = tagline
        this.title = title
        this.video = video
        this.voteAverage = voteAverage
        this.voteCount = voteCount
        this.imageBaseUrl = imageBaseUrl ?: ""
    }

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

    fun getCarouselImages() = listOf("https://image.tmdb.org/t/p/original/" + backdropPath, "https://image.tmdb.org/t/p/original/" + posterPath).map {
        CarouselItem(imageUrl = it)
    }

}
