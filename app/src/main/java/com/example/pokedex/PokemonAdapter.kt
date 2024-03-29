package com.example.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ItemPokemonBinding
import java.util.*

class PokemonAdapter : PagingDataAdapter<Result, PokemonAdapter.ViewHolder>(Result.ResultDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemPokemonBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    inner class ViewHolder(private val itemBinding: ItemPokemonBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(item: Result) {
            itemBinding.apply {
                Glide.with(imageView)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + (adapterPosition + 1) + ".png")
                    .into(imageView)

                val position = bindingAdapterPosition + 1
                tvName.text = "${String.format("%02d", position)} - " + (item.name?.capitalize(Locale.getDefault()) ?: "")
            }
        }
    }
}