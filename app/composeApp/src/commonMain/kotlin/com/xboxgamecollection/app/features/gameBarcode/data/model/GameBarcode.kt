package com.xboxgamecollection.app.features.gameBarcode.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GameBarcode(
    val id: Int,
    val description: String?,
    val upc: String,
    val game_id: String
)
