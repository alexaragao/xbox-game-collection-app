package com.xboxgamecollection.app.features.userCollection.domain.service

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.userCollection.data.model.AddGameToUserCollectionRequest

interface IUserCollectionService {
    suspend fun addGame(request: AddGameToUserCollectionRequest)

    suspend fun getAll(): List<Game>
}
