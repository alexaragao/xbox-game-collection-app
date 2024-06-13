package com.xboxgamecollection.app.features.game.data.service

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.service.IGameService
import com.xboxgamecollection.app.features.gameBarcode.data.model.GameBarcode
import com.xboxgamecollection.app.features.gameBarcode.domain.service.IGameBarcodeService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GameService (
    private val client: HttpClient
): IGameService {
    override suspend fun getById(gameId: String): Game? {
        return client.get("/games/${gameId}").body()
    }
}
