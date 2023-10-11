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
data class SpokenLanguage(
    @PrimaryKey(autoGenerate = true)
    val languageId: Int?,
    @Json(name = "english_name") var englishName : String? = null,
    @Json(name = "iso_639_1") var iso6391     : String? = null,
    @Json(name="name"         ) var name        : String? = null,
    @ColumnInfo(index = true)
    var movieId: Int?
)
