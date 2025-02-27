package com.dija.pokedex.data.repository

import com.dija.pokedex.data.database.PokemonDao
import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.data.network.ApiService

class PokemonRepository(private val pokemonDao: PokemonDao, private val apiService: ApiService) {

    suspend fun getPokemon(pokemonName: String): List<Pokemon> {
        val localPokemon = pokemonDao.getPokemonByName(pokemonName)
        return when(localPokemon.size) {
            0 -> fetchAndCachePokemon(pokemonName)
            else -> localPokemon
        }
    }

    private suspend fun fetchAndCachePokemon(pokemonName: String): List<Pokemon> {
        return try {
            val response = apiService.getPokemon(pokemonName)
            if(response.isSuccessful) {
                response.body()?.forEach {
                    pokemonDao.insertPokemon(it)
                }
                return response.body()!!
            } else {
                return emptyList()
            }
        } catch (ex:Exception) {
            pokemonDao.getAllSavedPokemon()
        }
    }

    suspend fun getAllSavedPokemon(): List<Pokemon> {
        return pokemonDao.getAllSavedPokemon()
    }
}

