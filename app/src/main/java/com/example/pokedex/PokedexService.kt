package com.example.pokedex

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {
        @GET("pokemon")
        suspend fun getPokemon(
                @Query("offset") offset: Int,
                @Query("limit") limit: Int
        ): Response<GetPokemonResponse>
    }