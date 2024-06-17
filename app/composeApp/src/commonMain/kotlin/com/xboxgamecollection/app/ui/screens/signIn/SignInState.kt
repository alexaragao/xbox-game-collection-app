package com.xboxgamecollection.app.ui.screens.signIn

data class SignInState(
    val nickname: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isSigningIn: Boolean = false,
    val errorMessage: String? = null
)
