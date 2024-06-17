package com.xboxgamecollection.app.features.gameList.screens

import androidx.compose.ui.text.input.TextFieldValue
import com.xboxgamecollection.app.features.game.data.model.Game

data class GameListState(
    val gameList: List<Game> = emptyList(),
    val search: TextFieldValue = TextFieldValue(""),
    val genre: String = "",
    val isLoading: Boolean = false
)
