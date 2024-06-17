package com.xboxgamecollection.app.features.gameList.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.xboxgamecollection.app.features.core.composables.BottomTab
import com.xboxgamecollection.app.features.core.composables.BottomTabItems
import com.xboxgamecollection.app.features.game.data.model.Game
import com.xboxgamecollection.app.features.game.domain.usecase.GetAllGamesUseCase
import org.koin.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    onNavigateToGameDetails: (String) -> Unit
) {
    val getAllGamesUseCase: GetAllGamesUseCase = getKoin().get()
    val viewModel = remember { GameListScreenViewModel(getAllGamesUseCase) }
    val state = viewModel.state

    Scaffold(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SearchBar(
                        textState = state.search,
                        onTextChanged = {
                            viewModel.onSearchChanged(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Title(text = "Game List")

                    Filters(onGenreChanged = viewModel::onGenreChanged)

                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    } else {
                        GameGrid(games = state.gameList, onGameSelected = onNavigateToGameDetails)
                    }
                }


            }
        },
        bottomBar = {
            CustomBottomAppBar()
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
fun SearchBar(textState: TextFieldValue, onTextChanged: (TextFieldValue) -> Unit) {
    TextField(
        value = textState,
        onValueChange = onTextChanged,
        placeholder = {
            Text(text = "Search for games...")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
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

@Composable
fun FilterButtons(
    filters: List<String>, selectedFilter: String?, onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(filters) { _, filter ->
            Button(
                onClick = { onFilterSelected(filter) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (filter == selectedFilter) MaterialTheme.colorScheme.primary else Color.Transparent,
                    contentColor = if (filter == selectedFilter) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp).height(40.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(text = filter)
            }
        }
    }
}

@Composable
fun Filters(onGenreChanged: (String) -> Unit) {
    val filters = listOf(
        "All",
        "Action",
        "Action-adventure",
        "Action RPG",
        "Adventure",
        "Bullet hell",
        "Card & Board",
        "First-person Shooter",
        "Hack & Slash",
        "Music",
        "Rhythm",
        "Open World",
        "Platformer",
        "Puzzle & Trivia",
        "Racing",
        "Shooter",
        "Simulation",
        "Sports",
        "Strategy & Simulation",
        "Survival Horror",
        "Third-person Shooter",
        "Western"
    )
    var selectedFilter by remember { mutableStateOf("All") }

    FilterButtons(filters = filters, selectedFilter = selectedFilter, onFilterSelected = { filter ->
        selectedFilter = filter
        onGenreChanged(filter)
    })
}

@Composable
fun CustomBottomAppBar() {
    BottomAppBar(
        modifier = Modifier.background(MaterialTheme.colorScheme.background).drawBehind {
            drawLine(
                color = Color.Gray,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 1.dp.toPx()
            )
        },
        content = {
            BottomTab(
                onNavigateToGameList = { },
                onNavigateToCollection = { },
                onNavigateToProfile = { },
                itemSelected = BottomTabItems.GAMES
            )
        }
    )
}
