package com.example.pokedex
import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
) {
    companion object ResultDiffUtil : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
    }
}