package com.example.pokedex.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import dagger.hilt.InstallIn
import androidx.paging.Pager
import com.example.pokedex.Result
import androidx.paging.PagingConfig
import com.example.pokedex.utils.paging.PokemonPagingSource
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PagerModule {
    @Provides
    @Named("pageSize")
    fun providePageSize(): Int = 20

    @Provides
    fun providePagingConfig(
        @Named("pageSize")
        pageSize: Int
    ): PagingConfig =
        PagingConfig(pageSize = pageSize)

    @Provides
    fun providePager(
        pagingConfig: PagingConfig,
        pagingSource: PokemonPagingSource
    ): Pager<Int, Result> =
        Pager(pagingConfig) {
            pagingSource
        }
}