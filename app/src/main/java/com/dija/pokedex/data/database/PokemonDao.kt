package com.dija.pokedex.data.database

import androidx.room.*
import com.dija.pokedex.data.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon_table WHERE name LIKE '%' || :pokemonName || '%'")
    suspend fun getPokemonByName(pokemonName: String): List<Pokemon>

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllSavedPokemon(): List<Pokemon>
}
