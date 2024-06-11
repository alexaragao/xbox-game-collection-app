package com.xboxgamecollection.api.repository

import com.xboxgamecollection.api.model.GameBarcode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GameBarcodeRepository : JpaRepository<GameBarcode, Long> {
    fun findByUpc(barcode: String): GameBarcode?
}
