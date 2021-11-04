package com.example.pokedex.utils.paging

import android.util.Log
import javax.inject.Inject
import androidx.paging.PagingState
import androidx.paging.PagingSource
import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.ResourceStatus
import com.example.pokedex.Result
import javax.inject.Named

class PokemonPagingSource @Inject constructor(
    private val pokedexRepository: PokedexRepository,
    @Named("pageSize")
    private val pageSize: Int
) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val key = params.key
        Log.d("Page", "$key")
        val response = pokedexRepository.getPokemon(offset = key ?: 0, limit = pageSize)

        if (response.status == ResourceStatus.SUCCESS) {
            val nextKey = if (response.data?.results?.size ?: 0 < pageSize) {
                null
            } else {
                (key ?: 0) + 20
            }
            Log.d("Next Key", "$nextKey")

            return LoadResult.Page(
                data = response.data!!.results!!,
                prevKey = null,
                nextKey = nextKey
            )
        } else {
            return LoadResult.Error(Exception(response.message ?: ""))
        }
    }
}