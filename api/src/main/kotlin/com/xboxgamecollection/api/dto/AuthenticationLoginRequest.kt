package com.xboxgamecollection.api.dto

data class AuthenticationLoginRequest(
    val nickname: String,
    val password: String
)
