package com.xboxgamecollection.app.network.services

import io.ktor.client.HttpClient
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

    defaultRequest {
        url {
            url("http://localhost:5000/")
        }
    }
}
