package com.example.pokedex.di

import com.example.pokedex.utils.paging.PokedexPaging
import com.example.pokedex.utils.paging.PokedexPagingImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokedexPagingModule {
    @Binds
    abstract fun bindPokedexPagingModule(pokedexPaging: PokedexPagingImpl): PokedexPaging
}