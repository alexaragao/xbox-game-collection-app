package com.xboxgamecollection.app.features.auth.domain.usecase

import com.xboxgamecollection.app.features.auth.data.model.AuthLoginRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthenticationResponse
import com.xboxgamecollection.app.features.auth.domain.repository.IAuthenticationRepository

class AuthSignInUseCase(
    private val authenticationRepository: IAuthenticationRepository
) {
    suspend operator fun invoke(request: AuthLoginRequest): AuthenticationResponse {
        return authenticationRepository.login(request)
    }
}
