package com.example.pokedex

import android.util.Log
import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val URL_API = "https://pokeapi.co/api/v2/"

        @Volatile
        private var INSTANCE: PokedexService? = null

        fun getApiClient(): PokedexService {
            return INSTANCE ?: synchronized(this) {
                val client = OkHttpClient.Builder().apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                            override fun log(message: String) {
                                Log.d("ApiClient", message)
                            }
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

                val instance = retrofit.create(PokedexService::class.java)
                INSTANCE = instance

                return instance
            }
        }
    }


}