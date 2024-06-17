package com.xboxgamecollection.app.network.services

import com.xboxgamecollection.app.database.TokenDataStore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

val apiClient = HttpClient {
    install(Logging) {
        level = LogLevel.HEADERS
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    install(Auth) {
        bearer {
            loadTokens {
                val accessToken = TokenDataStore.getAccessToken()
                val refreshToken = TokenDataStore.getRefreshToken()

                if (accessToken != null && refreshToken != null) BearerTokens(
                    accessToken,
                    refreshToken
                ) else null
            }
        }
    }

    defaultRequest {
        url {
            url("http://localhost:5000/")
        }
    }
}
