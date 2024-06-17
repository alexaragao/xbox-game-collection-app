package com.xboxgamecollection.app.features.game.data.service

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.service.IGameService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GameService (
    private val client: HttpClient
): IGameService {
    override suspend fun getById(gameId: String): Game? {
        return client.get("/games/${gameId}").body()
    }

    override suspend fun getAll(search: String?, genre: String?): List<Game> {
        return client.get("/games") {
            parameter("search", search)
            parameter("genre", genre)
        }.body()
    }
}
