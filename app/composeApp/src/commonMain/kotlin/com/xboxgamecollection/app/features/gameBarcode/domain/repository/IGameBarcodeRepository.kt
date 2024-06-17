package com.xboxgamecollection.app.features.gameBarcode.domain.repository

import com.xboxgamecollection.app.features.game.data.model.Game

interface IGameBarcodeRepository {
    suspend fun getGameByBarcode(barcode: String): Game?
}
