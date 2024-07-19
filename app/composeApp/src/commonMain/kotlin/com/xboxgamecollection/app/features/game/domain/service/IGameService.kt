package com.xboxgamecollection.app.features.game.domain.service

import com.xboxgamecollection.app.features.game.data.model.Game

interface IGameService {
    suspend fun getById(gameId: String): Game?
    
    suspend fun getAll(search: String?, genre: String?): List<Game>

    suspend fun getCollection(bearerAuth: String): List<Game>
}
