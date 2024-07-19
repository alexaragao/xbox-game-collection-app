package com.xboxgamecollection.app.features.userCollection.domain.repository

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.userCollection.data.model.AddGameToUserCollectionRequest

interface IUserCollectionRepository {
    suspend fun addGameToCollection(request: AddGameToUserCollectionRequest)

    suspend fun getCollection(): List<Game>
}
