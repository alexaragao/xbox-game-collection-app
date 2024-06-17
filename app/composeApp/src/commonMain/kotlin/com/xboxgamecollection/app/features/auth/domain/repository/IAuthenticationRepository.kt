package com.xboxgamecollection.app.features.auth.domain.repository

import com.xboxgamecollection.app.features.auth.data.model.AuthLoginRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthRegisterRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthenticationResponse

interface IAuthenticationRepository {
    suspend fun login(loginRequest: AuthLoginRequest): AuthenticationResponse

    suspend fun register(registerRequest: AuthRegisterRequest)

    suspend fun authenticateWithTokens(accessToken: String, refreshToken: String): AuthenticationResponse
}
