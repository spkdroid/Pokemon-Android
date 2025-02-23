package com.dija.pokedex.viewmodel

import com.dija.pokedex.data.model.Pokemon
import com.dija.pokedex.data.repository.PokemonRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _selectedPokemon = MutableLiveData<Pokemon?>()
    val selectedPokemon: LiveData<Pokemon?> = _selectedPokemon

    fun fetchPokemon(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.getPokemon(name)
                val result = response ?: emptyList()
                _pokemonList.postValue(result)
            } catch (e: Exception) {
                _pokemonList.postValue(emptyList())
            }
        }
    }

    fun selectPokemon(pokemon: Pokemon) {
        _selectedPokemon.value = pokemon
    }
}