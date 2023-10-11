package com.santos.awesomemovieapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.santos.awesomemovieapp.data.Genre

@Dao
interface BaseDao<T> {

    @Insert
    suspend fun insert(entity: T)

    @Insert
    suspend fun insertList(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun updateList(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun deleteList(entities: List<T>)
}
