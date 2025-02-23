package com.dija.pokedex.data.network

import com.dija.pokedex.data.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("findpokemon.php")
    suspend fun getPokemon(@Query("pokemon_name") name: String): Response<List<Pokemon>>
}