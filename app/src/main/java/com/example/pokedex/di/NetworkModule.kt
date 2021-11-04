package com.example.pokedex.di

import com.example.pokedex.PokedexService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import okhttp3.OkHttpClient
import dagger.hilt.InstallIn
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "https://pokeapi.co/api/v2/"

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(
        @Named("apiUrl")
        apiUrl: String,
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providePokedexService(retrofit: Retrofit): PokedexService =
        retrofit.create(PokedexService::class.java)
}