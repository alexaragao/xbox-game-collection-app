package com.xboxgamecollection.app.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xboxgamecollection.app.features.userCollection.domain.usecase.GetUserGamesCollectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CollectionScreenViewModel(
    private val getUserGamesCollectionUseCase: GetUserGamesCollectionUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionListState())
    val uiState: StateFlow<CollectionListState> = _uiState.asStateFlow()

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val collection = getUserGamesCollectionUseCase()
            _uiState.value = _uiState.value.copy(isLoading = false, collection = collection)
        }
    }

    fun onRefreshGames() {
        loadGames()
    }
}
