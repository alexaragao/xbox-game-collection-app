package com.xboxgamecollection.app.features.gameBarcode.data.service

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.gameBarcode.domain.service.IGameBarcodeService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class GameBarcodeService (
    private val client: HttpClient
): IGameBarcodeService {

    override suspend fun getGameByBarcode(barcode: String): Game? {
        return client.get("/barcodes/${barcode}").body()
    }

}
