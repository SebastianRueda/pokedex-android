package com.example.pokedex

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedex.data.remote.ApiClient
import java.lang.Exception

class PokemonPagingSource : PagingSource<Int, Result>() {
    companion object {
        private const val LIMIT = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val key = params.key
            Log.d("Page", "$key")
            val response = ApiClient.getApiClient().getPokemon(offset = key ?: 0, limit = LIMIT).results ?: listOf()

            val nextKey = if (response.size < LIMIT) {
                null
            } else {
                (key ?: 0) + LIMIT
            }
            Log.d("Next Key", "$nextKey")
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}