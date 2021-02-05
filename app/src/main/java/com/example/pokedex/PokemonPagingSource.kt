package com.example.pokedex

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

class PokemonPagingSource : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val key = params.key
            Log.d("Page", "$key")
            val response = ApiClient.getApiClient().getPokemon(offset = key ?: 0, limit = 20).results ?: listOf()

            val nextKey = if (response.size < 20) {
                null
            } else {
                (key ?: 0) + 20
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