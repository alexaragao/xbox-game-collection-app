package com.xboxgamecollection.app.di

import com.xboxgamecollection.app.features.auth.di.authenticationModule
import com.xboxgamecollection.app.features.game.di.gameModule
import com.xboxgamecollection.app.features.gameBarcode.di.gameBarcodeModule
import com.xboxgamecollection.app.features.userCollection.di.userCollectionModule

val appModules = listOf(
    authenticationModule,
    gameModule,
    gameBarcodeModule,
    userCollectionModule,
)
