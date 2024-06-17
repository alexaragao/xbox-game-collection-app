package com.xboxgamecollection.app.features.gameBarcode.domain.service

import com.xboxgamecollection.app.features.game.data.model.Game

interface IGameBarcodeService {
    suspend fun getGameByBarcode(barcode: String): Game?
}
