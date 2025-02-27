package com.dija.pokedex.repository

import com.dija.pokedex.data.database.PokemonDao
import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.data.network.ApiService
import com.dija.pokedex.data.repository.PokemonRepository
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*

import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokemonRepositoryTest {

    private lateinit var repository: PokemonRepository
    private lateinit var apiService: ApiService
    private lateinit var pokemonDao: PokemonDao

    @Before
    fun setup() {
        apiService = mock(ApiService::class.java)
        pokemonDao = mock(PokemonDao::class.java)
        repository = PokemonRepository(pokemonDao, apiService)
    }

    @Test
    fun `getPokemon should return Pokemon from API`() = runTest {
        val fakePokemon: List<Pokemon> = listOf(
            Pokemon(
                name = "Bulbasaur", id = "1", imageurl = "bulbasaur.png", height = "0.7m", weight = "6.9kg",
                category = "Seed", typeofpokemon = listOf("Grass", "Poison"), weaknesses = listOf("Fire", "Ice"),
                hp = 45, attack = 49, defense = 49, special_attack = 65, special_defense = 65, speed = 45,
                total = 318, abilities = listOf("Overgrow"), xdescription = "", ydescription = "", evolutions = listOf(),
                male_percentage = "", female_percentage = "", genderless = 0, cycles = "", egg_groups = "",
                evolvedfrom = "", reason = "", base_exp = 64
            )
        )


        val response = Response.success(fakePokemon)

        `when`(pokemonDao.getPokemonByName("Bulbasaur")).thenReturn(fakePokemon)

        `when`(apiService.getPokemon("Bulbasaur")).thenReturn(response)

        val result = repository.getPokemon("Bulbasaur")

        assertEquals(fakePokemon, result)
    }

    @Test
    fun `getPokemon should handle empty API response`() = runTest {
        val response: Response<List<Pokemon>> = Response.success(emptyList())

        `when`(pokemonDao.getPokemonByName("MissingNo")).thenReturn(emptyList())

        `when`(apiService.getPokemon("MissingNo")).thenReturn(response)

        val result = repository.getPokemon("MissingNo")

        assertEquals(emptyList<Pokemon>(), result)

    }

    @Test
    fun `getPokemon should handle API failure`() = runTest {
        `when`(apiService.getPokemon("Charmander")).thenThrow(RuntimeException("Network error"))

        `when`(pokemonDao.getPokemonByName("Charmander")).thenReturn(emptyList())

        try {
            repository.getPokemon("Charmander")
        } catch (e: Exception) {
            assertEquals("Network error", e.message)
        }
        verify(apiService).getPokemon("Charmander")
    }
}
