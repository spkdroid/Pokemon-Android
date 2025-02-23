package com.dija.pokedex.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dija.pokedex.viewmodel.PokemonViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PokemonDetailScreen(viewModel: PokemonViewModel) {
    val pokemon by viewModel.selectedPokemon.observeAsState()

    pokemon?.let {
        Column(Modifier.padding(16.dp)) {
            GlideImage(imageModel = it.imageurl, modifier = Modifier.size(120.dp))
            Text(text = it.name, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Height: ${it.height}")
            Text(text = "Weight: ${it.weight}")
            Text(text = "Abilities: ${it.abilities.joinToString(", ")}")
        }
    } ?: run {
        Text("No Pokémon selected", modifier = Modifier.padding(16.dp))
    }
}
