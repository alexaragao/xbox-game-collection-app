package com.xboxgamecollection.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.xboxgamecollection.app.database.TokenDataStore
import com.xboxgamecollection.app.features.auth.domain.usecase.AuthenticateWithTokensUseCase
import com.xboxgamecollection.app.navigation.AppScreen
import org.koin.compose.getKoin

@Composable
internal fun AppLoader(
    content: @Composable (startScreen: String) -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }

    val authenticateWithTokensUseCase: AuthenticateWithTokensUseCase = getKoin().get()

    val appViewModel = LocalAppViewModel.current

    LaunchedEffect(Unit) {
        val accessToken = TokenDataStore.getAccessToken()
        val refreshToken = TokenDataStore.getRefreshToken()

        if (accessToken != null && refreshToken != null) {
            try {
                val authResponse = authenticateWithTokensUseCase(accessToken, refreshToken)

                appViewModel.signInUser(
                    user = authResponse.user,
                    accessToken = authResponse.accessToken,
                    refreshToken = "" // TODO
                )
            } catch (exception: Exception) {
                println(exception)
            }
        }

        isLoading = false
    }

    val appState = appViewModel.state.value
    val startScreen =
        if (appState.isSignedIn) AppScreen.GameList.title
        else AppScreen.SignIn.title

    if (!isLoading) return content(startScreen)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.requiredSize(36.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
