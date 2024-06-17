package com.xboxgamecollection.app.ui.screens.gameList

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.features.game.domain.usecase.GetAllGamesUseCase
import com.xboxgamecollection.app.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin


class GameListScreenViewModel(
    private val navController: NavHostController
) : ViewModel() {
    val getAllGamesUseCase: GetAllGamesUseCase = getKoin().get()

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

    fun onSelectGame(gameId: String) {
        val route = AppScreen.GameDetails.title.replace("{gameId}", gameId)
        navController.navigate(route)
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

    fun onNavigateToScanner() {
        navController.navigate(AppScreen.BarcodeScanner.title)
    }
}
