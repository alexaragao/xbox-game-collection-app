package com.xboxgamecollection.app.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthLoginRequest(
    val nickname: String,
    val password: String,
)
