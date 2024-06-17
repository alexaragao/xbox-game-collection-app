package com.xboxgamecollection.app.di

import com.xboxgamecollection.app.features.auth.di.authenticationModule
import com.xboxgamecollection.app.features.game.di.gameModule
import com.xboxgamecollection.app.features.gameBarcode.di.gameBarcodeModule

val appModules = listOf(
    authenticationModule,
    gameModule,
    gameBarcodeModule
)
