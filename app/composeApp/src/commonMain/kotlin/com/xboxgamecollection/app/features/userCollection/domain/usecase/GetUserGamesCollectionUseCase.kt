package com.xboxgamecollection.app.features.userCollection.domain.usecase

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.userCollection.domain.repository.IUserCollectionRepository

class GetUserGamesCollectionUseCase(
    private val userCollectionRepository: IUserCollectionRepository
) {
    suspend operator fun invoke(): List<Game> {
        return userCollectionRepository.getCollection()
    }
}
