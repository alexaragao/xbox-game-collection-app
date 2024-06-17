package com.xboxgamecollection.app.features.auth.data.repository

import com.xboxgamecollection.app.features.auth.data.model.AuthLoginRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthRegisterRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthenticationResponse
import com.xboxgamecollection.app.features.auth.domain.repository.IAuthenticationRepository
import com.xboxgamecollection.app.features.auth.domain.service.IAuthenticationService

class AuthenticationRepository(
    private val authenticationService: IAuthenticationService
) : IAuthenticationRepository {
    override suspend fun login(loginRequest: AuthLoginRequest): AuthenticationResponse {
        return authenticationService.login(loginRequest)
    }

    override suspend fun register(registerRequest: AuthRegisterRequest) {
        return authenticationService.register(registerRequest)
    }

    override suspend fun authenticateWithTokens(
        accessToken: String,
        refreshToken: String
    ): AuthenticationResponse {
        return authenticationService.authenticateWithTokens(accessToken, refreshToken)
    }
}
