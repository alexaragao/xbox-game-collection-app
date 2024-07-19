package com.xboxgamecollection.app.ui.screens.scanResult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.xboxgamecollection.app.navigation.LocalNavController

@Composable
fun ScanResultScreen(stackEntry: NavBackStackEntry) {
    val barcode = stackEntry.arguments?.getString("barcode")

    ScanResultScreen(
        barcode = checkNotNull(barcode)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanResultScreen(barcode: String) {
    val navController = LocalNavController.current

    val viewModel = viewModel { ScanResultScreenModel(navController) }

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(barcode) {
        viewModel.getGameByBarcode(barcode)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Add game")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            if (uiState.game != null) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.width(120.dp).aspectRatio(0.71f),
                        contentScale = ContentScale.Fit,
                        model = uiState.game?.boxArtUrl,
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = uiState.state
            )

            TextField(
                value = uiState.game?.title ?: "",
                onValueChange = {},
                label = {
                    Text("Title")
                },
                readOnly = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = uiState.notes,
                onValueChange = { viewModel.onChangeNotes(it) },
                placeholder = {
                    Text("Notes about the item")
                },
                label = {
                    Text("Notes")
                },
                maxLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Column {
                Button(
                    onClick = { viewModel.addGameToCollection() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Add game to my collection")
                }
            }
        }
    }
}

