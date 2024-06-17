package com.xboxgamecollection.app.ui.screens.gameDetails

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.features.game.domain.usecase.GetGameByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.mp.KoinPlatform.getKoin

class GameDetailsScreenModel(
    private val navController: NavHostController
) : ViewModel() {
    val getGameByIdUseCase: GetGameByIdUseCase = getKoin().get()

    private val _uiState = MutableStateFlow<GameDetailsState>(GameDetailsState())
    val uiState: StateFlow<GameDetailsState> = _uiState.asStateFlow()

    suspend fun getGameById(gameId: String) {
        val game = getGameByIdUseCase(gameId)
        _uiState.update { it.copy(game = game) }
    }
}
