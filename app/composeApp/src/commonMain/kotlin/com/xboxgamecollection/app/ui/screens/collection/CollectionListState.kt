package com.xboxgamecollection.app.ui.screens.collection

import com.xboxgamecollection.app.features.game.data.model.Game

data class CollectionListState(
    val collection: List<Game> = emptyList(),
    val isLoading: Boolean = false
)
