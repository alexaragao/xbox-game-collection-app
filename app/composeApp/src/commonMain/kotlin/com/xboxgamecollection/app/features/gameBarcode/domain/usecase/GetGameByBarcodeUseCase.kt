package com.xboxgamecollection.app.features.gameBarcode.domain.usecase

import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.gameBarcode.domain.repository.IGameBarcodeRepository

class GetGameByBarcodeUseCase(
    private val gameBarcodeRepository: IGameBarcodeRepository
) {
    suspend operator fun invoke(barcode: String): Game? {
        return gameBarcodeRepository.getGameByBarcode(barcode)
    }
}
