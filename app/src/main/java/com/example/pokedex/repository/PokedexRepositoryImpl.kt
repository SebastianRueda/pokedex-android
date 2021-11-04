package com.example.pokedex.repository

import com.example.pokedex.GetPokemonResponse
import com.example.pokedex.PokedexService
import com.example.pokedex.Resource
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val pokedexService: PokedexService
) : PokedexRepository {
    override suspend fun getPokemon(offset: Int, limit: Int): Resource<GetPokemonResponse> {
        return try {
            val response = pokedexService.getPokemon(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                Resource.success(response.body()!!)
            } else {
                Resource.error(message = response.message())
            }
        } catch (e: Exception) {
            Resource.error(message = e.message)
        }

    }
}