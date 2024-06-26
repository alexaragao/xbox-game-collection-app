package com.xboxgamecollection.app.ui.screens.gameList

import androidx.compose.ui.text.input.TextFieldValue
import com.xboxgamecollection.app.features.game.data.model.Game

data class GameListState(
    val gameList: List<Game> = emptyList(),
    val search: TextFieldValue = TextFieldValue(""),
    val genre: String = "",
    val isLoading: Boolean = false
)
