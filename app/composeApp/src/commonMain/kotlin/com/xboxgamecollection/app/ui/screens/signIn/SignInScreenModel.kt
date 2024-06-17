package com.xboxgamecollection.app.ui.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.AppViewModel
import com.xboxgamecollection.app.features.auth.data.model.AuthLoginRequest
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthSignInUseCase
import com.xboxgamecollection.app.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class SignInScreenModel(
    private val navController: NavHostController,
    private val appViewModel: AppViewModel
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    val authSignInUseCase: AuthSignInUseCase = getKoin().get()

    fun onChangeNickname(value: String) {
        _uiState.update { it.copy(nickname = value) }
    }

    fun onChangePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun isSignInEnabled(): Boolean {
        if (uiState.value.password.length >= 6 && uiState.value.nickname.length >= 4) return true
        if (uiState.value.isSigningIn) return false

        return false
    }

    fun onSignIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSigningIn = true, errorMessage = null) }

            try {
                val requestBody = AuthLoginRequest(
                    nickname = uiState.value.nickname,
                    password = uiState.value.password,
                )

                val signInResponse = authSignInUseCase(requestBody)

                appViewModel.signInUser(
                    user = signInResponse.user,
                    accessToken = signInResponse.accessToken,
                    refreshToken = ""
                )

                navController.navigate(AppScreen.GameList.title)
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isSigningIn = false,
                        errorMessage = "Ops! Something went wrong"
                    )
                }
            }
        }
    }
}
