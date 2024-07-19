package com.xboxgamecollection.app.ui.screens.scanResult

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.features.gameBarcode.domain.usecase.GetGameByBarcodeUseCase
import com.xboxgamecollection.app.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.mp.KoinPlatform.getKoin

class ScanResultScreenModel(
    private val navController: NavHostController
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScanResultState())
    val uiState: StateFlow<ScanResultState> = _uiState.asStateFlow()

    val getGameByBarcodeUseCase: GetGameByBarcodeUseCase = getKoin().get()

    suspend fun getGameByBarcode(barcode: String) {
        val game = getGameByBarcodeUseCase(barcode) ?: return

        _uiState.update { it.copy(game = game) }
    }

    fun onChangeNotes(value: String) {
        _uiState.update { it.copy(notes = value) }
    }

    fun addGameToCollection() {
        // TODO
        navController.navigate(AppScreen.GameList.title)
    }
}
