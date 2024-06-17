package com.xboxgamecollection.app.ui.screens.barcodeScanner.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import com.xboxgamecollection.app.composables.BarcodeScanner

@Composable
fun BarcodeCameraMask(
    onScanBarcode: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        BarcodeScanner(
            modifier = Modifier.fillMaxSize(),
            onScanBarcode = onScanBarcode,
        )
        BarcodeMask(
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
private fun BarcodeMask(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val maskPath = Path().apply {
            val rectWidth = size.minDimension * 0.8f
            val rectHeight = rectWidth * 0.5f
            val rectLeft = (size.width - rectWidth) / 2
            val rectTop = (size.height - rectHeight) / 2

            addRoundRect(
                RoundRect(
                    left = rectLeft,
                    top = rectTop,
                    right = rectLeft + rectWidth,
                    bottom = rectTop + rectHeight,
                    cornerRadius = CornerRadius(20f)
                )
            )
        }

        clipPath(maskPath, clipOp = ClipOp.Difference) {
            drawRect(SolidColor(Color.Black.copy(alpha = 0.6f)))
        }
    }
}
