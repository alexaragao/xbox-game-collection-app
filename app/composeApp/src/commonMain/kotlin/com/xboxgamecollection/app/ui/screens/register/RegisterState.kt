package com.xboxgamecollection.app.ui.screens.register

data class RegisterState(
    val nickname: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isRegistering: Boolean = false,
    val errorMessage: String? = null
)
