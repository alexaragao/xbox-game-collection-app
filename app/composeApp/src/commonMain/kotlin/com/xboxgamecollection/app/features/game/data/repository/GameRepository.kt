package com.xboxgamecollection.app.features.game.data.repository

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.data.service.GameService
import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository
import com.xboxgamecollection.app.features.gameBarcode.data.service.GameBarcodeService
import com.xboxgamecollection.app.features.gameBarcode.domain.repository.IGameBarcodeRepository

class GameRepository(
    private val gameService: GameService
): IGameRepository {
    override suspend fun getById(gameId: String): Game? {
        return gameService.getById(gameId)
    }
}
