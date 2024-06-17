package com.xboxgamecollection.app.features.gameList.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.usecase.GetAllGamesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent


class GameListScreenViewModel(
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameListState())
    val uiState: StateFlow<GameListState> = _uiState.asStateFlow()

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val games = getAllGamesUseCase(
                _uiState.value.search.text.ifEmpty { null },
                if (_uiState.value.genre === "All") null else _uiState.value.genre
            )
            _uiState.value = _uiState.value.copy(gameList = games, isLoading = false)
        }
    }

    fun onSearchChanged(search: TextFieldValue) {
        _uiState.value = _uiState.value.copy(search = search)
        loadGames()
    }

    fun onGenreChanged(genre: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            _uiState.value = _uiState.value.copy(genre = genre)
            loadGames()
        }
    }
}
