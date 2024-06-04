package com.xboxgamecollection.api.dto

import com.xboxgamecollection.api.model.User

data class AuthenticationLoginResponse(
    val user: User,
    val accessToken: String
)
