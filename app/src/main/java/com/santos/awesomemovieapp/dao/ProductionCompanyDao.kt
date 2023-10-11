package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.santos.awesomemovieapp.data.ProductionCompany

@Dao
interface ProductionCompanyDao: BaseDao<ProductionCompany> {
    @Query("SELECT * FROM productioncompany")
    suspend fun getAllProductionCompany(): List<ProductionCompany>
    @Query("SELECT * FROM productioncompany where id = :productionCompanyId")
    suspend fun getProductionCompany(productionCompanyId: Int): ProductionCompany
}