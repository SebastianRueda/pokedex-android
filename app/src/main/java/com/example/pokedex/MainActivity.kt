package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.utils.GridSpacingItemDecoration
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }
    private val adapter by lazy { PokemonAdapter() }
    private val pokemons = mutableListOf<Result>()
// test 4
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val gridLayout = GridLayoutManager(this@MainActivity, 2)
            recyclerView.layoutManager = gridLayout
            recyclerView.addItemDecoration(GridSpacingItemDecoration(2, resources.getDimensionPixelOffset(R.dimen.item_spacing), true))
            recyclerView.adapter = adapter

            lifecycleScope.launchWhenStarted {
                viewModel.pokemons.collectLatest {
                    /*when (it.status) {
                        ResourceStatus.SUCCESS -> {
                            if (pokemons.size == 0) {
                                progressbar.isVisible = false
                                recyclerView.isVisible = true
                            }

                            pokemons.addAll(it.data!!)
                            adapter.submitData(ArrayList(pokemons))
                        }
                        ResourceStatus.ERROR -> {
                            Toast.makeText(this@MainActivity, it.message!!, Toast.LENGTH_SHORT).show()
                        }
                        ResourceStatus.LOADING -> {
                            if (pokemons.size == 0) {
                                progressbar.isVisible = true
                                recyclerView.isVisible = false
                            }
                        }
                    }*/
                    adapter.submitData(it)
                }

            }

            /*viewModel.getPokemons()

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val last = gridLayout.findLastVisibleItemPosition()
                    if (last == pokemons.size - 1) {
                        viewModel.getPokemons()
                    }
                }
            })*/
        }
    }
}