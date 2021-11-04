package com.example.pokedex.ui

import android.app.Application
import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.pokedex.base.BaseViewModel
import com.example.pokedex.Resource
import com.example.pokedex.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val pager: Pager<Int, Result>
) : BaseViewModel(application) {
    var pokemons: Flow<PagingData<Result>> = pager.flow
}