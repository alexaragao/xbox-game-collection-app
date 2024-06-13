package com.xboxgamecollection.app.features.game.domain.repository

import com.xboxgamecollection.app.features.game.data.model.Game

interface IGameRepository {
    suspend fun getById(gameId: String): Game?
}
