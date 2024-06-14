package com.xboxgamecollection.app.features.gameList.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen() {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchBar(textState, onTextChanged = { textState = it })

                Spacer(modifier = Modifier.height(16.dp))

                Title(text = "Game List")

                Filters()

                GameGrid()
            }
        },
        bottomBar = {
            CustomBottomAppBar()
        })
}

@Composable
fun SearchBar(textState: TextFieldValue, onTextChanged: (TextFieldValue) -> Unit) {
    TextField(
        value = textState,
        onValueChange = onTextChanged,
        placeholder = {
            Text(text = "Pesquisar jogos")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = "Search Icon"
            )
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
fun GameGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(9) { index ->
            GameItem(
                // TODO: Replace with response API
                imageUrl = "https://m.media-amazon.com/images/I/91ParGOiL-S._AC_UF1000,1000_QL80_.jpg",
                description = "Game $index"
            )
        }
    }
}

@Composable
fun GameItem(imageUrl: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(6.dp)
    ) {
        Box {
            AsyncImage(
                model = imageUrl,
                contentDescription = description,
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Composable
fun FilterButtons(
    filters: List<String>, selectedFilter: String?, onFilterSelected: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
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
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 8.dp)
                    .height(40.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.extraLarge
            ) {
                Text(text = filter)
            }
        }
    }
}

@Composable
fun Filters() {
    val filters = listOf("All", "FPS", "Adventure", "Point & Click", "Sports")
    var selectedFilter by remember { mutableStateOf("All") }

    FilterButtons(filters = filters, selectedFilter = selectedFilter, onFilterSelected = { filter ->
        selectedFilter = filter
        // TODO: Handle filter selection
    })
}

@Composable
fun CustomBottomAppBar() {
    BottomAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
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
        })
}