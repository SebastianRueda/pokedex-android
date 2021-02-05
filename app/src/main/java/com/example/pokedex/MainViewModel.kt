package com.example.pokedex

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        private const val LIMIT = 20
    }

    @ExperimentalCoroutinesApi
    val pokemonsStateFlow by lazy { MutableStateFlow<Resource<List<Result>>>(Resource.loading()) }
    val pokemons = Pager(PagingConfig(pageSize = LIMIT, maxSize = 1000, enablePlaceholders = false)) {
        PokemonPagingSource()
    }.flow
    private var offset: Int = 0

    @ExperimentalCoroutinesApi
    fun getPokemons() {
        viewModelScope.launch {
            try {
                offset += LIMIT
                Log.d("OFFSET", "$offset")
                val response = ApiClient.getApiClient().getPokemon(offset = offset, limit = LIMIT)
                pokemonsStateFlow.value = Resource.success(response.results ?: listOf())
            } catch (e: Exception) {
                offset -= LIMIT
                pokemonsStateFlow.value = Resource.error("Error")
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun getPokemons2() = flow {
        try {
            offset += LIMIT
            Log.d("OFFSET", "$offset")
            val response = ApiClient.getApiClient().getPokemon(offset = offset, limit = LIMIT)
            emit(Resource.success(response.results ?: listOf()))
        } catch (e: Exception) {
            offset -= LIMIT
            emit(Resource.error("Error"))
        }

    }
}