package com.xboxgamecollection.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel

val LocalAppViewModel = compositionLocalOf<AppViewModel> {
    error("Invalid view model")
}

@Composable
fun AppViewModelProvider(content: @Composable (AppViewModel) -> Unit) {
    val appViewModel: AppViewModel = viewModel { AppViewModel() }

    CompositionLocalProvider(LocalAppViewModel provides appViewModel) {
        content(appViewModel)
    }
}
