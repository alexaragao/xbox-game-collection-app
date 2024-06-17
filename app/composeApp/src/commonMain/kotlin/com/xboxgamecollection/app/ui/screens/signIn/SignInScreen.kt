package com.xboxgamecollection.app.ui.screens.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xboxgamecollection.app.LocalAppViewModel
import com.xboxgamecollection.app.navigation.LocalNavController
import org.jetbrains.compose.resources.vectorResource
import x360_collection.composeapp.generated.resources.Res
import x360_collection.composeapp.generated.resources.ic_app

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen() {
    val appViewModel = LocalAppViewModel.current
    val navController = LocalNavController.current

    val viewModel = viewModel { SignInScreenModel(navController, appViewModel) }
    val uiState by viewModel.uiState.collectAsState()

    val errorMessage = uiState.errorMessage

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Sign in") },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                modifier = Modifier
                    .align(Alignment.End)
                    .requiredSize(144.dp)
                    .offset(x = 30.dp),
                contentScale = ContentScale.Inside,
                imageVector = vectorResource(Res.drawable.ic_app),
                contentDescription = null
            )

            Text(
                text = "Hello! ...Erm, what was your login again?",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Sign in to your account",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                TextField(
                    value = uiState.nickname,
                    onValueChange = { viewModel.onChangeNickname(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nickname") },
                    placeholder = { Text("Nickname") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                TextField(
                    value = uiState.password,
                    onValueChange = { viewModel.onChangePassword(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    placeholder = { Text("Password") },
                    visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    maxLines = 1,
                    trailingIcon = {
                        IconButton(
                            onClick = { viewModel.togglePasswordVisibility() }
                        ) {
                            Icon(
                                imageVector = if (uiState.isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null,
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { viewModel.onSignIn() })
                )

                if (errorMessage != null) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (uiState.isSigningIn) {
                    CircularProgressIndicator()
                }

                if (!uiState.isSigningIn) {
                    Button(
                        onClick = { viewModel.onSignIn() },
                        enabled = viewModel.isSignInEnabled(),
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text("Next")
                    }
                }
            }
        }
    }
}
