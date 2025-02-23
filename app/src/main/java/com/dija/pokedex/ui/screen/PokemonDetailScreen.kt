package com.dija.pokedex.ui.screen

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.dija.pokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(pokemonName: String, viewModel: PokemonViewModel) {
    // Observing the Pokémon list and finding the selected Pokémon
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    val pokemon = pokemonList.find { it.name == pokemonName }

    // Layout for the Pokémon details
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        pokemon?.let {
            // Pokémon Image
            Image(
                painter = rememberAsyncImagePainter(it.imageurl),
                contentDescription = it.name,
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Pokémon Name
            Text(text = "Name: ${it.name}", fontSize = 24.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(8.dp))

            // Pokémon Height and Weight
            Text(text = "Height: ${it.height}")
            Text(text = "Weight: ${it.weight}")

            Spacer(modifier = Modifier.height(8.dp))

            // Pokémon Description
            Text(text = "Description: ${it.xdescription}")
            Text(text = "Category: ${it.category}")
            Text(text = "Abilities: ${it.abilities.joinToString()}")
        } ?: Text("Pokemon not found!")
    }
}
