package com.dija.pokedex.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberImagePainter
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.viewmodel.PokemonViewModel

@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonViewModel
) {

    val pokemonList by viewModel.pokemonList.observeAsState(emptyList())
    val pokemon = pokemonList.firstOrNull { it.name == pokemonName }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Pokémon Image
            Image(
                painter = rememberImagePainter(pokemon!!.imageurl),
                contentDescription = "Pokemon Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Pokémon Name
            Text(
                text = pokemon!!.name,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            )
        }

        item {
            // Pokémon ID
            Text(
                text = "ID: ${pokemon!!.id}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Displaying Stats with a Bar Graph
            Text(
                text = "Stats",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            if (pokemon != null) {
                StatBarChart(pokemon)
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Additional Pokémon Information
            if (pokemon != null) {
                PokemonInfo(pokemon)
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            // Evolution Info
            if (pokemon != null) {
                EvolutionInfo(pokemon)
            }
        }
    }
}

@Composable
fun StatBarChart(pokemon: Pokemon) {
    val stats = listOf(
        "HP" to pokemon.hp,
        "Attack" to pokemon.attack,
        "Defense" to pokemon.defense,
        "Special Attack" to pokemon.special_attack,
        "Special Defense" to pokemon.special_defense,
        "Speed" to pokemon.speed
    )

    // Using a simple bar chart to represent stats
    Column {
        stats.forEach { stat ->
            StatBar(stat.first, stat.second)
        }
    }
}

@Composable
fun StatBar(statName: String, statValue: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = statName,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth(0.8f)
                .background(Color.Gray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(statValue / 255f) // Normalize value to max 255
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}

@Composable
fun PokemonInfo(pokemon: Pokemon) {
    Column {
        // Height
        InfoRow("Height", pokemon.height)

        // Weight
        InfoRow("Weight", pokemon.weight)

        // Category
        InfoRow("Category", pokemon.category)

        // Egg Groups
        InfoRow("Egg Groups", pokemon.egg_groups)

        // Abilities
        InfoRow("Abilities", pokemon.abilities.joinToString(", "))

        // Weaknesses
        InfoRow("Weaknesses", pokemon.weaknesses.joinToString(", "))
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun EvolutionInfo(pokemon: Pokemon) {
    Column {
        Text(
            text = "Evolution",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        // Add evolution info here
    }
}
