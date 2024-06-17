package com.xboxgamecollection.app.composables

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.xboxgamecollection.app.analyzer.BarcodeAnalyzer

@Composable
actual fun BarcodeScanner(
    modifier: Modifier,
    onScanBarcode: (String) -> Unit
) {
    val localContext = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current

    val previewView = remember { PreviewView(localContext) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(localContext) }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            // Barcode Scanner setup
            val resolutionSelector = ResolutionSelector.Builder()
                .setResolutionStrategy(
                    ResolutionStrategy(
                        Size(720, 1280),
                        ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER_THEN_HIGHER
                    )
                ).build()

            val imageAnalysis = ImageAnalysis.Builder()
                .setResolutionSelector(resolutionSelector)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            val barcodeAnalyzer = BarcodeAnalyzer(onScanBarcode = onScanBarcode)

            imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(context), barcodeAnalyzer)

            cameraProvider.bindToLifecycle(lifeCycleOwner, cameraSelector, preview, imageAnalysis)

            previewView
        },
    )
}
