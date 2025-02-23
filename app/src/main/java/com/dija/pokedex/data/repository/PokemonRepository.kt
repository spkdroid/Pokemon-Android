package com.dija.pokedex.data.repository

import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.data.network.ApiService
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPokemon(name: String): List<Pokemon>? {
        val response = apiService.getPokemon(name)
        return if (response.isSuccessful) response.body() else null
    }
}
