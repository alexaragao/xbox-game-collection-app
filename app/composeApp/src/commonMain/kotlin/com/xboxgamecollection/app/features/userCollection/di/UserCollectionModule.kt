package com.xboxgamecollection.app.features.userCollection.di

import com.xboxgamecollection.app.features.userCollection.data.repository.UserCollectionRepository
import com.xboxgamecollection.app.features.userCollection.data.service.UserCollectionService
import com.xboxgamecollection.app.features.userCollection.domain.repository.IUserCollectionRepository
import com.xboxgamecollection.app.features.userCollection.domain.service.IUserCollectionService
import com.xboxgamecollection.app.features.userCollection.domain.usecase.AddGameToUserCollectionUseCase
import com.xboxgamecollection.app.features.userCollection.domain.usecase.GetUserGamesCollectionUseCase
import com.xboxgamecollection.app.network.services.apiClient
import org.koin.dsl.module

val userCollectionModule = module {
    single { apiClient }
    single<IUserCollectionService> { UserCollectionService(get()) }
    single<IUserCollectionRepository> { UserCollectionRepository(get()) }
    single { AddGameToUserCollectionUseCase(get()) }
    single { GetUserGamesCollectionUseCase(get()) }
}
