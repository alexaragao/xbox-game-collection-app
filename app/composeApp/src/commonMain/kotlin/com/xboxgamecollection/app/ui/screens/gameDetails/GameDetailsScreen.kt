package com.xboxgamecollection.app.ui.screens.gameDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import coil3.compose.AsyncImage
import com.xboxgamecollection.app.navigation.AppScreen
import com.xboxgamecollection.app.navigation.LocalNavController

@Composable
fun GameDetailsScreen(stackEntry: NavBackStackEntry) {
    val gameId = stackEntry.arguments?.getString("gameId")

    GameDetailsScreen(
        gameId = checkNotNull(gameId)
    )
}

@Composable
fun GameDetailsScreen(gameId: String) {
    val navController = LocalNavController.current

    val viewModel = viewModel { GameDetailsScreenModel(navController) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(gameId) {
        viewModel.getGameById(gameId)
    }

    val game = uiState.game ?: return

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f)
            ) {
                Box {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth().height(210.dp),
                        contentScale = ContentScale.FillWidth,
                        model = game.coverUrl,
                        contentDescription = null
                    )

                    AsyncImage(
                        modifier = Modifier.width(120.dp).aspectRatio(0.71f)
                            .align(Alignment.BottomCenter)
                            .offset(y = 32.dp),
                        contentScale = ContentScale.Fit,
                        model = game.boxArtUrl,
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.headlineSmall,
                    )


                    Text(
                        text = game.releaseDate,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        text = game.description,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (false) { // TODO: Check if game is in user collection
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CheckCircle,
                                tint = MaterialTheme.colorScheme.primary,
                                contentDescription = null
                            )

                            Text(
                                text = "You already got this game",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }

            Column {
                HorizontalDivider()

                Button(
                    onClick = { navController.navigate(AppScreen.BarcodeScanner.title) },
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingBag,
                        contentDescription = null
                    )

                    Text(text = "Adicionar a minha coleção")
                }
            }
        }
    }
}
