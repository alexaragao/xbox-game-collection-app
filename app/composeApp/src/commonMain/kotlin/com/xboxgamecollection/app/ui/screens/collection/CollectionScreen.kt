package com.xboxgamecollection.app.ui.screens.collection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.xboxgamecollection.app.features.core.composables.BottomTab
import com.xboxgamecollection.app.features.core.composables.BottomTabItems
import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.usecase.GetCollectionUseCase
import org.koin.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionScreen(
    onNavigateToGameDetails: (String) -> Unit
) {
    val getCollectionUseCase: GetCollectionUseCase = getKoin().get()
    val viewModel = remember { CollectionScreenViewModel(getCollectionUseCase) }
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Title(text = "Collection")

                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    } else if (state.collection.isNullOrEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "No games in collection",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    } else {
                        GameGrid(games = state.collection!!, onGameSelected = onNavigateToGameDetails)
                    }
                }
            }
        },
        bottomBar = {
            BottomTab(
                itemSelected = BottomTabItems.COLLECTION
            )
        }
    )
}

@Composable
fun GameGrid(games: List<Game>, onGameSelected: (String) -> Unit = {}) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
    ) {
        itemsIndexed(games) { _, game ->
            GameItem(
                imageUrl = game.boxArtUrl,
                description = game.title,
                onClick = { onGameSelected(game.id) }
            )
        }
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun GameItem(imageUrl: String, description: String, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth().aspectRatio(0.71f).padding(6.dp),
        onClick = onClick
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}
