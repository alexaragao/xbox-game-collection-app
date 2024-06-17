package com.xboxgamecollection.app.features.gameBarcode.data.repository

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.gameBarcode.domain.repository.IGameBarcodeRepository
import com.xboxgamecollection.app.features.gameBarcode.domain.service.IGameBarcodeService

class GameBarcodeRepository(
    private val gameBarcodeService: IGameBarcodeService
): IGameBarcodeRepository {

    override suspend fun getGameByBarcode(barcode: String): Game? {
        return gameBarcodeService.getGameByBarcode(barcode)
    }

}
