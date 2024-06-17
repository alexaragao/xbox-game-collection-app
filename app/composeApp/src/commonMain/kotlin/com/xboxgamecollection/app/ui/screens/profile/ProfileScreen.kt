package com.xboxgamecollection.app.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xboxgamecollection.app.LocalAppViewModel
import com.xboxgamecollection.app.features.core.composables.BottomTab
import com.xboxgamecollection.app.features.core.composables.BottomTabItems
import com.xboxgamecollection.app.navigation.LocalNavController

@Composable
fun ProfileScreen() {
    val appViewModel = LocalAppViewModel.current
    val navController = LocalNavController.current

    val viewModel = viewModel { ProfileScreenModel(navController) }

    val appUser = appViewModel.state.value.user

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomTab(
                itemSelected = BottomTabItems.PROFILE
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Hello, ${appUser?.nickname}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextButton(
                    onClick = { viewModel.onSignOut() }
                ) {
                    Text("Sign out")
                }
            }
        }
    }
}
