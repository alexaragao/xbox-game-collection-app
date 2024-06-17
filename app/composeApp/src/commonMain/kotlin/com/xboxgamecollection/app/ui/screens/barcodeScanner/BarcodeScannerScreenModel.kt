package com.xboxgamecollection.app.ui.screens.barcodeScanner

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.xboxgamecollection.app.navigation.AppScreen


class BarcodeScannerScreenModel(
    private val navController: NavHostController
) : ViewModel() {
    fun onScanBarcode(barcode: String) {
        val route = AppScreen.ScanResult.title.replace("{barcode}", barcode)
        navController.navigate(route)
    }
}
