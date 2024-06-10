package com.xboxgamecollection.api.controller

import com.xboxgamecollection.api.model.Game
import com.xboxgamecollection.api.service.GameBarcodeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/barcodes")
class GameBarcodeController(
    private val gameBarcodeService: GameBarcodeService
) {

    @GetMapping("/{barcode}")
    fun getGameByBarcode(@PathVariable barcode: String): ResponseEntity<Game> {
        val game = gameBarcodeService.findGameByBarcode(barcode)

        return if (game != null) {
            ResponseEntity.ok(game)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

}
