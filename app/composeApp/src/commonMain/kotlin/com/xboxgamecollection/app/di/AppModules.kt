package com.xboxgamecollection.app.di

import com.xboxgamecollection.app.features.auth.di.authenticationModule
import com.xboxgamecollection.app.features.game.di.gameModule

val appModules = listOf(
    authenticationModule,
    gameModule,
)
