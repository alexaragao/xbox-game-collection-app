package com.xboxgamecollection.app.features.userCollection.domain.usecase

import com.xboxgamecollection.app.features.userCollection.data.model.AddGameToUserCollectionRequest
import com.xboxgamecollection.app.features.userCollection.domain.repository.IUserCollectionRepository

class AddGameToUserCollectionUseCase(
    private val userCollectionRepository: IUserCollectionRepository
) {
    suspend operator fun invoke(request: AddGameToUserCollectionRequest) {
        return userCollectionRepository.addGameToCollection(request)
    }
}
