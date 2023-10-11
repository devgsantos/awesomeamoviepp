package com.santos.awesomemovieapp.data

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithAllProperties(
    @Embedded var movie: Movie,
    @Relation(
        entity = Genre::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var genres: List<Genre>,
    @Relation(
        entity = ProductionCompany::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var productionCompany: List<ProductionCompany>,
    @Relation(
        entity = ProductionCountry::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var productionCountry: List<ProductionCountry>,
    @Relation(
        entity = SpokenLanguage::class,
        parentColumn = "id",
        entityColumn = "movieId"
    ) var spokenLanguage: List<SpokenLanguage>
) {

}