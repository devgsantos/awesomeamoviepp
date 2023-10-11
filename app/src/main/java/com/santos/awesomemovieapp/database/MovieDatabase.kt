package com.santos.awesomemovieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.santos.awesomemovieapp.dao.GenreDao
import com.santos.awesomemovieapp.dao.MovieDao
import com.santos.awesomemovieapp.dao.ProductionCompanyDao
import com.santos.awesomemovieapp.dao.ProductionCountryDao
import com.santos.awesomemovieapp.dao.SpokenLanguageDao
import com.santos.awesomemovieapp.data.Genre
import com.santos.awesomemovieapp.data.Movie
import com.santos.awesomemovieapp.data.ProductionCompany
import com.santos.awesomemovieapp.data.ProductionCountry
import com.santos.awesomemovieapp.data.SpokenLanguage

@Database(
    entities = [
        Movie::class,
        Genre::class,
        ProductionCompany::class,
        ProductionCountry::class,
        SpokenLanguage::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(movieDatabase: MovieDatabase): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun productionCompanyDao(): ProductionCompanyDao
    abstract fun productionCountryDao(): ProductionCountryDao
    abstract fun spokenLanguageDao(): SpokenLanguageDao

    companion object{
        @Volatile
        private var instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_data_base"
                ).build()
                this.instance = database
                return database
            }
        }
    }

}