package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.santos.awesomemovieapp.data.ProductionCountry

@Dao
interface ProductionCountryDao: BaseDao<ProductionCountry> {
    @Query("SELECT * FROM productioncountry")
    suspend fun getAllProductionCountry() : List<ProductionCountry>
    @Query("SELECT * FROM productioncompany where id = :productionCountryId")
    suspend fun getProductionCountry(productionCountryId: Int): ProductionCountry
}