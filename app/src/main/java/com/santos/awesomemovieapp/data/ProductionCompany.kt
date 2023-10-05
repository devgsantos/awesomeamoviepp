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
data class ProductionCompany (
    @PrimaryKey
    @Json(name="id"             ) var id            : Int?    = null,
    @Json(name="logo_path"      ) var logoPath      : String? = null,
    @Json(name="name"           ) var name          : String? = null,
    @Json(name="origin_country" ) var originCountry : String? = null,
    @ColumnInfo(index = true)
    val movieId: Int?
)