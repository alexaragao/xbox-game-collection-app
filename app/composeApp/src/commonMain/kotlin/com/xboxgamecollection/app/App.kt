package com.xboxgamecollection.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.xboxgamecollection.app.di.appModules
import com.xboxgamecollection.app.ui.screens.gameList.GameListScreen
import com.xboxgamecollection.app.navigation.AppScreen
import com.xboxgamecollection.app.navigation.NavControllerProvider
import com.xboxgamecollection.app.theme.AppTheme
import com.xboxgamecollection.app.ui.screens.register.RegisterScreen
import com.xboxgamecollection.app.ui.screens.barcodeScanner.BarcodeScannerScreen
import com.xboxgamecollection.app.ui.screens.gameDetails.GameDetailsScreen
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
                            GameListScreen()
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

                        composable(
                            route = AppScreen.GameDetails.title,
                            arguments = listOf(
                                navArgument("gameId") { type = NavType.StringType }
                            ),
                            content = { GameDetailsScreen(it) }
                        )
                    }
                }
            }
        }
    }
}

internal expect fun openUrl(url: String?)
