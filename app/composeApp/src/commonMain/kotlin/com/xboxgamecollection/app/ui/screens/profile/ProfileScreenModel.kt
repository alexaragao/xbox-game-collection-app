package com.xboxgamecollection.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.database.TokenDataStore
import com.xboxgamecollection.app.navigation.AppScreen
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val navController: NavHostController
) : ViewModel() {
    fun onSignOut() {
        viewModelScope.launch {
            TokenDataStore.clearTokens()
            navController.navigate(AppScreen.SignIn.title)
        }
    }
}
