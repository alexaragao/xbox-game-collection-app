package com.xboxgamecollection.app.features.userCollection.data.repository

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.userCollection.data.model.AddGameToUserCollectionRequest
import com.xboxgamecollection.app.features.userCollection.domain.repository.IUserCollectionRepository
import com.xboxgamecollection.app.features.userCollection.domain.service.IUserCollectionService

class UserCollectionRepository(
    private val userCollectionService: IUserCollectionService
) : IUserCollectionRepository {
    override suspend fun addGameToCollection(request: AddGameToUserCollectionRequest) {
        return userCollectionService.addGame(request)
    }

    override suspend fun getCollection(): List<Game> {
        return userCollectionService.getAll()
    }
}
