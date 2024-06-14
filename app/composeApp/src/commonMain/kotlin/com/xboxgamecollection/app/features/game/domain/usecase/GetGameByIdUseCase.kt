package com.xboxgamecollection.app.features.game.domain.usecase

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository

class GetGameByIdUseCase(
    private val gameRepository: IGameRepository
) {
    suspend operator fun invoke(gameId: String): Game? {
        return gameRepository.getById(gameId)
    }
}
