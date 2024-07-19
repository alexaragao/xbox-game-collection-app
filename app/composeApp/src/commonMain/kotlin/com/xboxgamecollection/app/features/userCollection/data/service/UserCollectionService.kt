package com.xboxgamecollection.app.features.userCollection.data.service

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.userCollection.data.model.AddGameToUserCollectionRequest
import com.xboxgamecollection.app.features.userCollection.domain.service.IUserCollectionService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class UserCollectionService(
    private val client: HttpClient
) : IUserCollectionService {
    override suspend fun addGame(request: AddGameToUserCollectionRequest) {
        return client.post("/user/game/collection") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun getAll(): List<Game> {
        return client.get("/user/game/collection").body()
    }
}
