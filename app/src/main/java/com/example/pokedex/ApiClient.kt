package com.example.pokedex

import android.util.Log
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ApiClient {
    companion object {
        private const val URL_API = "https://pokeapi.co/api/v2/"

        @Volatile
        private var INSTANCE: ApiService? = null

        fun getApiClient(): ApiService {
            return INSTANCE ?: synchronized(this) {
                val client = OkHttpClient.Builder().apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                            Log.d("ApiClient", it)
                        }).apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                    }
                }.build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(URL_API)
                        .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

                val instance = retrofit.create(ApiService::class.java)
                INSTANCE = instance

                return instance
            }
        }
    }

    interface ApiService {
        @GET("pokemon")
        suspend fun getPokemon(
                @Query("offset") offset: Int,
                @Query("limit") limit: Int
        ): Any
    }
}