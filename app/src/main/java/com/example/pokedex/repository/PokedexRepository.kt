package com.example.pokedex.repository

import com.example.pokedex.GetPokemonResponse
import com.example.pokedex.Resource

interface PokedexRepository {
    suspend fun getPokemon(offset: Int, limit: Int): Resource<GetPokemonResponse>
}