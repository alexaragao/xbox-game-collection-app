package com.xboxgamecollection.app.features.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val nickname: String,
    val password: String,
    val createdAt: String,
    val updatedAt: String
)
