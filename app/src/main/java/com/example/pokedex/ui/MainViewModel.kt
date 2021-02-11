package com.example.pokedex.ui

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.pokedex.base.BaseViewModel
import com.example.pokedex.PokemonPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        private const val LIMIT = 20
    }

    @ExperimentalCoroutinesApi
    val pokemonsFlow = Pager(PagingConfig(pageSize = LIMIT, enablePlaceholders = false)) {
        PokemonPagingSource()
    }.flow
}