package com.xboxgamecollection.app.features.game.data.repository

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.data.service.GameService
import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository
import com.xboxgamecollection.app.features.game.domain.service.IGameService

class GameRepository(
    private val gameService: IGameService
) : IGameRepository {
    override suspend fun getById(gameId: String): Game? {
        return gameService.getById(gameId)
    }

    override suspend fun getAll(search: String?, genre: String?): List<Game> {
        return gameService.getAll(search, genre)
    }
}
