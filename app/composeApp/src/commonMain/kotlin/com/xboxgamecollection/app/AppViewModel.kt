package com.xboxgamecollection.app

import androidx.lifecycle.ViewModel
import com.xboxgamecollection.app.database.TokenDataStore
import com.xboxgamecollection.app.features.auth.data.model.User
import com.xboxgamecollection.app.features.auth.domain.usecase.InstallAuthBearerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.mp.KoinPlatform.getKoin

data class AppState(
    var user: User? = null,
    val isSignedIn: Boolean = false,
)

class AppViewModel : ViewModel() {
    private val _state = MutableStateFlow(AppState())
    val state: StateFlow<AppState> = _state.asStateFlow()

    val installAuthBearerUseCase: InstallAuthBearerUseCase = getKoin().get()

    suspend fun signInUser(user: User, accessToken: String, refreshToken: String) {
        installAuthBearerUseCase(accessToken, refreshToken)
        TokenDataStore.storeTokens(accessToken, refreshToken)

        _state.update { it.copy(user = user, isSignedIn = true) }
    }
}
