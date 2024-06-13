package com.xboxgamecollection.app.features.game.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: String,
    val title: String,
    val description: String,
    val publisher: String,
    val developer: String,
    val genre: String,
    val boxArtUrl: String,
    val coverUrl: String,
    val rewardsUrl: String,
    val isPhysical: Boolean,
    val isDigital: Boolean,
    val releaseDate: String,
    val createdAt: String,
    val updatedAt: String,
)
