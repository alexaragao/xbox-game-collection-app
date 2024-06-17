package com.xboxgamecollection.app.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRegisterRequest(
    val nickname: String,
    val password: String
)
