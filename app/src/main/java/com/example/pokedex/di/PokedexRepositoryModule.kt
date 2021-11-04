package com.example.pokedex.di

import com.example.pokedex.repository.PokedexRepository
import com.example.pokedex.repository.PokedexRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PokedexRepositoryModule {
    @Binds
    abstract fun bindPokedexRepository(pokedexRepositoryImpl: PokedexRepositoryImpl): PokedexRepository
}