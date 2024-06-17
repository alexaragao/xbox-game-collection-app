package com.xboxgamecollection.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xboxgamecollection.app.di.appModules
import com.xboxgamecollection.app.features.gameList.screens.GameListScreen
import com.xboxgamecollection.app.navigation.AppScreen
import com.xboxgamecollection.app.navigation.NavControllerProvider
import com.xboxgamecollection.app.theme.AppTheme
import com.xboxgamecollection.app.ui.screens.register.RegisterScreen
import com.xboxgamecollection.app.ui.screens.barcodeScanner.BarcodeScannerScreen
import com.xboxgamecollection.app.ui.screens.start.StartScreen
import com.xboxgamecollection.app.ui.screens.profile.ProfileScreen
import com.xboxgamecollection.app.ui.screens.signIn.SignInScreen
import org.koin.compose.KoinApplication

@Composable
internal fun App() = AppTheme {
    KoinApplication(application = { modules(appModules) }) {
        NavControllerProvider { navController ->
            AppViewModelProvider {
                AppLoader { startScreen ->
                    NavHost(
                        navController = navController,
                        startDestination = startScreen
                    ) {
                        composable(route = AppScreen.GameList.title) {
                            GameListScreen(onNavigateToGameDetails = {})
                        }

                        composable(
                            route = AppScreen.SignIn.title,
                            content = { SignInScreen() }
                        )

                        composable(
                            route = AppScreen.Profile.title,
                            content = { ProfileScreen() }
                        )

                        composable(
                            route = AppScreen.Register.title,
                            content = { RegisterScreen() }
                        )

                        composable(route = AppScreen.BarcodeScanner.title) {
                            BarcodeScannerScreen()
                        }

                        composable(route = AppScreen.Start.title) {
                            StartScreen(
                                onNavigateToSignIn = { navController.navigate(AppScreen.SignIn.title) },
                                onNavigateToSignUp = { navController.navigate(AppScreen.Register.title) }
                            )
                        }
                    }
                }
            }
        }
    }
}

internal expect fun openUrl(url: String?)
