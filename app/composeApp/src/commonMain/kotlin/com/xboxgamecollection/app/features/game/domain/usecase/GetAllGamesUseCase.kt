package com.xboxgamecollection.app.features.game.domain.usecase

import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository
import com.xboxgamecollection.app.features.game.data.model.Game

class GetAllGamesUseCase(
    private val gameRepository: IGameRepository
) {
    suspend operator fun invoke(search: String?, genre: String?): List<Game> {
        return gameRepository.getAll(search, genre)
    }
}