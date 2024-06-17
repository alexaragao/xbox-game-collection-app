package com.xboxgamecollection.app.features.auth.domain.usecase

import com.xboxgamecollection.app.features.auth.data.model.AuthenticationResponse
import com.xboxgamecollection.app.features.auth.data.model.User
import com.xboxgamecollection.app.features.auth.domain.repository.IAuthenticationRepository

class AuthenticateWithTokensUseCase(
    private val authenticationRepository: IAuthenticationRepository
) {
    suspend operator fun invoke(accessToken: String, refreshToken: String): AuthenticationResponse {
        return authenticationRepository.authenticateWithTokens(accessToken, refreshToken)
    }
}
