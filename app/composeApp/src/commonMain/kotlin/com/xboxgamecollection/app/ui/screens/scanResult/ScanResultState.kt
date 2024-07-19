package com.xboxgamecollection.app.ui.screens.scanResult

import com.xboxgamecollection.app.features.game.data.model.Game

data class ScanResultState(
    var state: String = "",
    val game: Game? = null,
    val notes: String = "",
    val isBusyAddingGame: Boolean = false
)
