package com.xboxgamecollection.app.features.game.di

import com.xboxgamecollection.app.features.game.data.repository.GameRepository
import com.xboxgamecollection.app.features.game.data.service.GameService
import com.xboxgamecollection.app.features.game.domain.repository.IGameRepository
import com.xboxgamecollection.app.features.game.domain.service.IGameService
import com.xboxgamecollection.app.features.game.domain.usecase.GetAllGamesUseCase
import com.xboxgamecollection.app.features.game.domain.usecase.GetGameByIdUseCase
import com.xboxgamecollection.app.network.services.apiClient
import org.koin.dsl.module

val gameModule = module {
    single { apiClient }
    single<IGameService> { GameService(get()) }
    single<IGameRepository> { GameRepository(get()) }
    single { GetGameByIdUseCase(get()) }
    single { GetAllGamesUseCase(get()) }
}
