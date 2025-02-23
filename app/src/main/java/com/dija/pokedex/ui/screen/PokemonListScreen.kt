package com.dija.pokedex.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.viewmodel.PokemonViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun PokemonListScreen(viewModel: PokemonViewModel) {
    var searchQuery by remember { mutableStateOf("Pikachu") }

    // Observe LiveData from ViewModel
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    LaunchedEffect(searchQuery) {
        viewModel.fetchPokemon(searchQuery)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Enter Pokémon Name") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(Modifier.fillMaxSize()) {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon, onClick = { viewModel.selectPokemon(pokemon) })
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(Modifier.padding(16.dp)) {
            GlideImage(imageModel = pokemon.imageurl, modifier = Modifier.size(64.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = pokemon.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "ID: ${pokemon.id}")
            }
        }
    }
}
