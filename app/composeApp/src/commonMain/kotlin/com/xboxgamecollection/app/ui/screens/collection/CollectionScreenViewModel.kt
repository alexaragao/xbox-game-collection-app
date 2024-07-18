package com.xboxgamecollection.app.ui.screens.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xboxgamecollection.app.database.TokenDataStore
import com.xboxgamecollection.app.features.game.domain.usecase.GetCollectionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CollectionScreenViewModel(
    private val getCollectionsUseCase: GetCollectionUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CollectionListState())
    val uiState: StateFlow<CollectionListState> = _uiState.asStateFlow()

    init {
        loadGames()
    }

    private fun loadGames() {
        viewModelScope.launch {
            val accessToken = TokenDataStore.getAccessToken()
            _uiState.value = _uiState.value.copy(isLoading = true)
            val collection = accessToken?.let { getCollectionsUseCase(it) }
            _uiState.value = _uiState.value.copy(isLoading = false, collection = collection)
        }
    }
}
