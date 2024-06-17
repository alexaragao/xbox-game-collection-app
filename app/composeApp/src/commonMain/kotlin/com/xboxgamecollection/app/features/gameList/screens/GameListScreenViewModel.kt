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
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

data class GameListState(
    val gameList: List<Game> = emptyList(),
    val search: TextFieldValue = TextFieldValue(""),
    val genre: String = "",
    val isLoading: Boolean = false
)

class GameListScreenViewModel(
    private val getAllGamesUseCase: GetAllGamesUseCase
) : ViewModel() {
    var state by mutableStateOf(GameListState())
        private set

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val games = getAllGamesUseCase(
                state.search.text,
                if (state.genre == "All") null else state.genre
            )
            state = state.copy(gameList = games, isLoading = false)
        }
    }

    fun onSearchChanged(search: TextFieldValue) {
        state = state.copy(search = search)
        loadGames()
    }

    fun onGenreChanged(genre: String) {
        state = state.copy(genre = genre)
        loadGames()
    }
}
