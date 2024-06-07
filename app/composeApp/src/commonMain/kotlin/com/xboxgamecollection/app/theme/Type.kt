package com.xboxgamecollection.app.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import x360_collection.composeapp.generated.resources.Res
import x360_collection.composeapp.generated.resources.Sora_Bold
import x360_collection.composeapp.generated.resources.Sora_ExtraBold
import x360_collection.composeapp.generated.resources.Sora_ExtraLight
import x360_collection.composeapp.generated.resources.Sora_Light
import x360_collection.composeapp.generated.resources.Sora_Medium
import x360_collection.composeapp.generated.resources.Sora_Regular
import x360_collection.composeapp.generated.resources.Sora_SemiBold
import x360_collection.composeapp.generated.resources.Sora_Thin

@Composable
fun SoraFontFamily() = FontFamily(
    Font(Res.font.Sora_Thin, weight = FontWeight.Thin),
    Font(Res.font.Sora_ExtraLight, weight = FontWeight.ExtraLight),
    Font(Res.font.Sora_Light, weight = FontWeight.Light),
    Font(Res.font.Sora_Regular, weight = FontWeight.Normal),
    Font(Res.font.Sora_Medium, weight = FontWeight.Medium),
    Font(Res.font.Sora_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Sora_Bold, weight = FontWeight.Bold),
    Font(Res.font.Sora_ExtraBold, weight = FontWeight.ExtraBold),
)

@Composable
fun AppTypography() = Typography().run {
    val bodyFontFamily = SoraFontFamily()

    val displayFontFamily = SoraFontFamily()

    copy(
        displayLarge = displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = labelSmall.copy(fontFamily = bodyFontFamily)
    )
}

