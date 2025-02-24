package com.dija.pokedex.repository

import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.data.network.ApiService
import com.dija.pokedex.data.repository.PokemonRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

class PokemonRepositoryTest {

    private lateinit var repository: PokemonRepository
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        apiService = mock(ApiService::class.java)
        repository = PokemonRepository(apiService)
    }

    @Test
    fun `getPokemonList should return Pokemon list from API`() = runBlocking {
        val fakePokemonList = listOf(
            Pokemon(name = "Bulbasaur", id = "1", imageurl = "bulbasaur.png", height = "0.7m", weight = "6.9kg",
                category = "Seed", typeofpokemon = listOf("Grass", "Poison"), weaknesses = listOf("Fire", "Ice"),
                hp = 45, attack = 49, defense = 49, special_attack = 65, special_defense = 65, speed = 45,
                total = 318, abilities = listOf("Overgrow"), xdescription = "", ydescription = "", evolutions = listOf(),
                male_percentage = "", female_percentage = "", genderless = 0, cycles = "", egg_groups = "", evolvedfrom = "", reason = "", base_exp = 64
            )
        )

        val response = Response.success(fakePokemonList)

        `when`(apiService.getPokemon("Bulbasaur")).thenReturn(response)

        val result = repository.getPokemon("Bulbasaur")

        assertEquals(fakePokemonList, result)
    }
}
