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
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberImagePainter(pokemon!!.imageurl),
                    contentDescription = "Pokemon Image",
                    modifier = Modifier
                        .size(350.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
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
    val maxStatValue = 100f // Pokémon stats usually have a max of 255
    val percentage = statValue / maxStatValue

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Row for the stat name and bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Stat Name (aligned left)
            Text(
                text = statName,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f) // Pushes everything else to the right
            )

            // Stat Bar Container
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .weight(4f) // Gives the bar a good proportion relative to text
                    .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
            ) {
                // Filled portion of the bar
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(percentage)
                        .background(getStatBarColor(statName), RoundedCornerShape(8.dp))
                )
            }

            // Stat Percentage (aligned right)
            Text(
                text = "${statValue}",
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .padding(start = 8.dp) // Adds spacing between the bar and percentage
            )
        }
    }
}


@Composable
fun getStatBarColor(statName: String): Color {
     return when (statName) {
        "HP" -> Color.Green
        "Attack" -> Color.Red
        "Defense" -> Color.Blue
        "Special Attack" -> Color.Magenta
        "Special Defense" -> Color.Yellow
        "Speed" -> Color.Cyan
        else -> Color.Gray
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
