package com.dija.pokedex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.viewmodel.PokemonViewModel
import javax.inject.Inject

@Composable
fun PokemonListScreen (
    navController: NavController,
    viewModel: PokemonViewModel
) {
    // State to hold the search query
    var searchQuery by remember { mutableStateOf("") }

    // Observing LiveData from the ViewModel
    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())

    // Handle the search query change
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            viewModel.fetchPokemon(searchQuery)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Pokedex",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Search Bar with search icon
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
            },
            label = { Text("Search Pokémon") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.small
        )

        // LazyColumn to display Pokémon list
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(pokemonList) { pokemon ->
                PokemonItem(pokemon) {
                    navController.navigate("pokemonDetail/${pokemon.name}")
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Pokémon Image
            Image(
                painter = rememberImagePainter(pokemon.imageurl),
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Pokémon Information
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "ID: ${pokemon.id}",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.Gray)
                )
                Text(
                    text = "Type: ${pokemon.typeofpokemon.joinToString(", ")}",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color.Gray)
                )
            }
        }
    }
}