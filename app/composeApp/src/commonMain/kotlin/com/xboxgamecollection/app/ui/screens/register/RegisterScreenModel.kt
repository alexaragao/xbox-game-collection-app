package com.xboxgamecollection.app.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.features.auth.data.model.AuthRegisterRequest
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthRegisterUseCase
import com.xboxgamecollection.app.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class RegisterScreenModel(
    private val navController: NavHostController
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()

    val authRegisterUseCase: AuthRegisterUseCase = getKoin().get()

    fun onChangeNickname(value: String) {
        _uiState.update { it.copy(nickname = value) }
    }

    fun onChangePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onChangeConfirmPassword(value: String) {
        _uiState.update { it.copy(confirmPassword = value) }
    }

    fun isRegisterEnabled(): Boolean {
        if (uiState.value.password.length < 6 && uiState.value.nickname.length < 4) return false
        if (uiState.value.password != uiState.value.confirmPassword) return false

        return true
    }

    fun onSignIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRegistering = true, errorMessage = null) }

            try {
                val requestBody = AuthRegisterRequest(
                    nickname = uiState.value.nickname,
                    password = uiState.value.password,
                )

                authRegisterUseCase(requestBody)

                navController.navigate(AppScreen.SignIn.title)
            } catch (exception: Exception) {
                _uiState.update {
                    it.copy(
                        isRegistering = false,
                        errorMessage = "Ops! Something went wrong"
                    )
                }
            }
        }
    }
}
