package com.xboxgamecollection.app.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRegisterResponse(
    val user: User,
    val accessToken: String
)
