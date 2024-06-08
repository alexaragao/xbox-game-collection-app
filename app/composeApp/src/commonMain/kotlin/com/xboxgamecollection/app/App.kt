package com.xboxgamecollection.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.xboxgamecollection.app.navigation.AppScreen
import com.xboxgamecollection.app.navigation.NavControllerProvider
import com.xboxgamecollection.app.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    NavControllerProvider { navController ->
        NavHost(
            navController = navController,
            startDestination = AppScreen.Home.title
        ) {
            composable(route = AppScreen.Home.title) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("Home Screen")

                    Button(
                        onClick = { navController.navigate(AppScreen.GameList.title) }
                    ) {
                        Text("Go to GameList")
                    }
                }
            }

            composable(route = AppScreen.GameList.title) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("GameList Screen")

                    Button(
                        onClick = { navController.popBackStack() }
                    ) {
                        Text("Go to Home")
                    }
                }
            }
        }
    }
}

internal expect fun openUrl(url: String?)
