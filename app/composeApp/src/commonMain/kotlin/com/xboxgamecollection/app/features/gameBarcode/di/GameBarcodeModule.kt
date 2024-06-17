package com.xboxgamecollection.app.features.gameBarcode.di

import com.xboxgamecollection.app.features.gameBarcode.data.repository.GameBarcodeRepository
import com.xboxgamecollection.app.features.gameBarcode.data.service.GameBarcodeService
import com.xboxgamecollection.app.features.gameBarcode.domain.repository.IGameBarcodeRepository
import com.xboxgamecollection.app.features.gameBarcode.domain.service.IGameBarcodeService
import com.xboxgamecollection.app.features.gameBarcode.domain.usecase.GetGameByBarcodeUseCase
import com.xboxgamecollection.app.network.services.apiClient
import org.koin.dsl.module

val gameBarcodeModule = module {
    single { apiClient }
    single<IGameBarcodeService> { GameBarcodeService(get()) }
    single<IGameBarcodeRepository> { GameBarcodeRepository(get()) }
    single { GetGameByBarcodeUseCase(get()) }
}
