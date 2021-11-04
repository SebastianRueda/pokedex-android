package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.Result
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.utils.GridSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val adapter by lazy { PokemonAdapter() }
    private val pokemons = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val gridLayout = GridLayoutManager(this@MainActivity, 2)
            recyclerView.layoutManager = gridLayout
            recyclerView.addItemDecoration(GridSpacingItemDecoration(2, resources.getDimensionPixelOffset(
                R.dimen.item_spacing
            ), true))
            recyclerView.adapter = adapter

            lifecycleScope.launchWhenStarted {
                viewModel.pokemons.collectLatest {

                    adapter.submitData(it)
                }

            }
        }
    }
}