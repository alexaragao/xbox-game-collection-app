package com.xboxgamecollection.app.features.auth.domain.usecase

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer

class InstallAuthBearerUseCase(
    private val client: HttpClient
) {
    operator fun invoke(accessToken: String, refreshToken: String) {
        client.config {
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(accessToken, refreshToken)
                    }
                }
            }
        }
    }
}
