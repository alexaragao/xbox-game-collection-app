package com.xboxgamecollection.app.features.core.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xboxgamecollection.app.navigation.AppScreen
import com.xboxgamecollection.app.navigation.LocalNavController
import org.jetbrains.compose.resources.painterResource
import x360_collection.composeapp.generated.resources.Res
import x360_collection.composeapp.generated.resources.account_circle
import x360_collection.composeapp.generated.resources.control
import x360_collection.composeapp.generated.resources.library_add

@Composable
fun BottomTab(
    itemSelected: BottomTabItems
) {
    val navController = LocalNavController.current

    fun onNavigateToGameList() = navController.navigate(AppScreen.GameList.title)

    fun onNavigateToCollection() = navController.navigate(AppScreen.Collection.title)

    fun onNavigateToProfile() = navController.navigate(AppScreen.Profile.title)

    val items = listOf(
        BottomBarItem(
            title = "Games",
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.control),
                    contentDescription = "Games"
                )
            },
            onClick = { onNavigateToGameList() },
            isSelected = itemSelected == BottomTabItems.GAMES
        ),
        BottomBarItem(
            title = "Collection",
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.library_add),
                    contentDescription = "Collection"
                )
            },
            onClick = { onNavigateToCollection() },
            isSelected = itemSelected == BottomTabItems.COLLECTION
        ),
        BottomBarItem(
            title = "Profile",
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.account_circle),
                    contentDescription = "Profile"
                )
            },
            onClick = { onNavigateToProfile() },
            isSelected = itemSelected == BottomTabItems.PROFILE
        )
    )

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
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { item ->
                    BottomTabButton(
                        title = item.title,
                        icon = item.icon,
                        onClick = item.onClick,
                        isSelected = item.isSelected
                    )
                }
            }
        }
    )
}

@Composable
fun BottomTabButton(
    title: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            icon()
            Text(text = title)
        }
    }
}

enum class BottomTabItems {
    GAMES, COLLECTION, PROFILE
}

data class BottomBarItem(
    val title: String,
    val icon: @Composable () -> Unit,
    val onClick: () -> Unit,
    val isSelected: Boolean
)

