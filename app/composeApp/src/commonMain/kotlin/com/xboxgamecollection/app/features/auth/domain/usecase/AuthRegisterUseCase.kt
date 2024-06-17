package com.xboxgamecollection.app.features.auth.domain.usecase

import com.xboxgamecollection.app.features.auth.data.model.AuthRegisterRequest
import com.xboxgamecollection.app.features.auth.domain.repository.IAuthenticationRepository

class AuthRegisterUseCase(
    private val authenticationRepository: IAuthenticationRepository
) {
    suspend operator fun invoke(request: AuthRegisterRequest) {
        return authenticationRepository.register(request)
    }
}
