package com.santos.awesomemovieapp.di

import android.content.Context
import androidx.room.Room
import com.santos.awesomemovieapp.dao.BaseDao
import com.santos.awesomemovieapp.dao.GenreDao
import com.santos.awesomemovieapp.dao.MovieDao
import com.santos.awesomemovieapp.dao.ProductionCompanyDao
import com.santos.awesomemovieapp.dao.ProductionCountryDao
import com.santos.awesomemovieapp.dao.SpokenLanguageDao
import com.santos.awesomemovieapp.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideComicDao(db: MovieDatabase): MovieDao =
        db.movieDao(db)

    @Provides
    fun providesGenreDao(db: MovieDatabase): GenreDao =
        db.genreDao()

    @Provides
    fun providesProductionCompanyDao(db: MovieDatabase): ProductionCompanyDao =
        db.productionCompanyDao()

    @Provides
    fun providesProductionCountryDao(db: MovieDatabase): ProductionCountryDao =
        db.productionCountryDao()

    @Provides
    fun providesSpokenLanguageDao(db: MovieDatabase): SpokenLanguageDao =
        db.spokenLanguageDao()

    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ): MovieDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_data_base"
        ).build()
}