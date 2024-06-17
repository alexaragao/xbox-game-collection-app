package com.xboxgamecollection.app.ui.screens.barcodeScanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xboxgamecollection.app.navigation.LocalNavController
import com.xboxgamecollection.app.ui.screens.barcodeScanner.composables.BarcodeCameraMask

@Composable
fun BarcodeScannerScreen() {
    val navController = LocalNavController.current

    val viewModel = viewModel { BarcodeScannerScreenModel(navController) }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            BarcodeCameraMask(
                modifier = Modifier.fillMaxSize(),
                onScanBarcode = { viewModel.onScanBarcode(it) },
            )

            Surface(
                modifier = Modifier.align(Alignment.Center).offset(y = (-200).dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(
                    text = "Point your cellphone\nat the game's barcode",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp, 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
