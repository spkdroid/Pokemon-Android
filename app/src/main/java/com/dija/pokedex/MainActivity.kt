package com.dija.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dija.pokedex.ui.screen.PokemonDetailScreen
import com.dija.pokedex.ui.screen.PokemonListScreen
import com.dija.pokedex.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PokemonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "pokemonList") {
                composable("pokemonList") {
                    PokemonListScreen(navController, viewModel)
                }
                composable("pokemonDetail/{pokemonName}") { backStackEntry ->
                    val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: ""
                    PokemonDetailScreen(pokemonName, viewModel)
                }
            }
        }
    }
}