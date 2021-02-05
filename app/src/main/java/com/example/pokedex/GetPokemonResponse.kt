package com.example.pokedex
import com.google.gson.annotations.SerializedName

data class GetPokemonResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: Any? = null,
    @SerializedName("results")
    val results: List<Result>? = null
)