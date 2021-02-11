package com.example.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.filter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex.R
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.utils.GridSpacingItemDecoration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter by lazy { PokemonAdapter() }

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
                viewModel.pokemonsFlow.collectLatest {
                    adapter.submitData(it)
                }
            }
        }
    }
}