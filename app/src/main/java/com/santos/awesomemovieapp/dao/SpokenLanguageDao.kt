package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Query
import com.santos.awesomemovieapp.data.SpokenLanguage

@Dao
interface SpokenLanguageDao: BaseDao<SpokenLanguage> {
    @Query("SELECT * FROM spokenlanguage")
    suspend fun getAllSpokenLanguage(): List<SpokenLanguage>
    @Query("SELECT * FROM spokenlanguage where languageId = :spokenLanguageId")
    suspend fun getSpokenLanguage(spokenLanguageId: Int): SpokenLanguage
}