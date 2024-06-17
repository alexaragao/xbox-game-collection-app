package com.xboxgamecollection.app.features.auth.domain.usecase

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth

class RemoveAuthBearerUseCase(
    private val client: HttpClient
) {
    operator fun invoke(gameId: String) {
        client.config {
            install(Auth) {}
        }
    }
}
