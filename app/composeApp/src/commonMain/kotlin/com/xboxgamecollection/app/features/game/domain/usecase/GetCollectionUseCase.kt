package com.xboxgamecollection.app.features.game.domain.usecase

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository

class GetCollectionUseCase(
    private val gameRepository: IGameRepository
) {
    suspend operator fun invoke(bearerAuth: String): List<Game> {
        return gameRepository.getCollection(bearerAuth)
    }
}