package com.santos.awesomemovieapp.di

import com.santos.awesomemovieapp.api.MovieService
import com.santos.awesomemovieapp.data.ApiCredentials
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
   fun provideRetrofit() =
       Retrofit.Builder()
           .baseUrl(ApiCredentials.baseApiUrl)
           .addConverterFactory(MoshiConverterFactory.create(moshi))
           .build()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
       retrofit.create(MovieService::class.java)

}