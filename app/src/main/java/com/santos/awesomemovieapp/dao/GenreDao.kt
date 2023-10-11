package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.santos.awesomemovieapp.data.Genre

@Dao
interface GenreDao: BaseDao<Genre> {

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<Genre>

    @Query("SELECT * FROM genre where id = :genreId")
    suspend fun getGenre(genreId: Int): Genre
}