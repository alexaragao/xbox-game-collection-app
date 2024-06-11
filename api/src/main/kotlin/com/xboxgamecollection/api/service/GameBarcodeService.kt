package com.xboxgamecollection.api.service

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.repository.GameBarcodeRepository
import org.springframework.stereotype.Service

@Service
class GameBarcodeService(
    private val gameBarcodeRepository: GameBarcodeRepository
) {

    fun findGameByBarcode(barcode: String): Game? {
        val gameBarcode = gameBarcodeRepository.findByUpc(barcode)

        return gameBarcode?.game
    }

}
