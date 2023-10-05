package com.santos.awesomemovieapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Genre(
    @PrimaryKey
    @Json(name="id"   ) var id   : Int?    = null,
    @Json(name="name" ) var name : String? = null,
    @ColumnInfo(index = true)
    val movieId: Int?

)
