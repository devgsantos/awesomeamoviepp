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
data class ProductionCountry(
    @PrimaryKey(autoGenerate = true)
    var countryId: Int?,
    @Json(name="iso_3166_1" ) var iso31661 : String? = null,
    @Json(name="name"       ) var name     : String? = null,
    @ColumnInfo(index = true)
    var movieId: Int?
)
