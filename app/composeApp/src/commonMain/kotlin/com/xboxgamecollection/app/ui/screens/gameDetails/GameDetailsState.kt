package com.xboxgamecollection.app.ui.screens.gameDetails

import com.xboxgamecollection.app.features.game.data.model.Game

data class GameDetailsState(
    val state: String = "",
    val game: Game? = null
)
