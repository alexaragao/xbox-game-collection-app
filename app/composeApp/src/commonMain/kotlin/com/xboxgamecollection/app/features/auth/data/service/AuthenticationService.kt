package com.xboxgamecollection.app.features.auth.data.service

import com.xboxgamecollection.app.features.auth.data.model.AuthLoginRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthRegisterRequest
import com.xboxgamecollection.app.features.auth.data.model.AuthenticationResponse
import com.xboxgamecollection.app.features.auth.domain.service.IAuthenticationService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationService (
    private val client: HttpClient
): IAuthenticationService {
    override suspend fun login(loginRequest: AuthLoginRequest): AuthenticationResponse {
        val response = client.post("/login") {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }

        return response.body()
    }

    override suspend fun register(registerRequest: AuthRegisterRequest) {
        println(registerRequest)
        client.post("/register") {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }
    }

    override suspend fun authenticateWithTokens(accessToken: String, refreshToken: String): AuthenticationResponse {
        val response = client.post("/auth") {
            bearerAuth(accessToken)
        }

        return response.body()
    }
}
