package com.xboxgamecollection.app.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BarcodeScanner(
    modifier: Modifier = Modifier,
    onScanBarcode: (String) -> Unit,
)
