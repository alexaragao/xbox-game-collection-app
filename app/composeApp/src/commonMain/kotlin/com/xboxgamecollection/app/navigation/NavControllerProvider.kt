package com.xboxgamecollection.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// Composition local for NavHostController
val LocalNavController = compositionLocalOf<NavHostController> {
    error("NavController not provided")
}

// Composition provider for NavHostController
@Composable
fun NavControllerProvider(content: @Composable (NavHostController) -> Unit) {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        content(navController)
    }
}
