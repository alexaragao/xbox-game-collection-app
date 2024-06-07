package com.xboxgamecollection.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*

private val LightColorScheme = lightColorScheme(
    primary = theme_light_primary,
    onPrimary = theme_light_onPrimary,
    primaryContainer = theme_light_primaryContainer,
    onPrimaryContainer = theme_light_onPrimaryContainer,
    secondary = theme_light_secondary,
    onSecondary = theme_light_onSecondary,
    secondaryContainer = theme_light_secondaryContainer,
    onSecondaryContainer = theme_light_onSecondaryContainer,
    tertiary = theme_light_tertiary,
    onTertiary = theme_light_onTertiary,
    tertiaryContainer = theme_light_tertiaryContainer,
    onTertiaryContainer = theme_light_onTertiaryContainer,
    error = theme_light_error,
    errorContainer = theme_light_errorContainer,
    onError = theme_light_onError,
    onErrorContainer = theme_light_onErrorContainer,
    background = theme_light_background,
    onBackground = theme_light_onBackground,
    surface = theme_light_surface,
    onSurface = theme_light_onSurface,
    surfaceVariant = theme_light_surfaceVariant,
    onSurfaceVariant = theme_light_onSurfaceVariant,
    outline = theme_light_outline,
    inverseOnSurface = theme_light_inverseOnSurface,
    inverseSurface = theme_light_inverseSurface,
    inversePrimary = theme_light_inversePrimary,
    outlineVariant = theme_light_outlineVariant,
    scrim = theme_light_scrim,
    surfaceBright = theme_light_surfaceBright,
    surfaceContainer = theme_light_surfaceContainer,
    surfaceContainerHigh = theme_light_surfaceContainerHigh,
    surfaceContainerHighest = theme_light_surfaceContainerHighest,
    surfaceContainerLow = theme_light_surfaceContainerLow,
    surfaceContainerLowest = theme_light_surfaceContainerLowest,
    surfaceDim = theme_light_surfaceDim,
)

private val DarkColorScheme = darkColorScheme(
    primary = theme_dark_primary,
    onPrimary = theme_dark_onPrimary,
    primaryContainer = theme_dark_primaryContainer,
    onPrimaryContainer = theme_dark_onPrimaryContainer,
    secondary = theme_dark_secondary,
    onSecondary = theme_dark_onSecondary,
    secondaryContainer = theme_dark_secondaryContainer,
    onSecondaryContainer = theme_dark_onSecondaryContainer,
    tertiary = theme_dark_tertiary,
    onTertiary = theme_dark_onTertiary,
    tertiaryContainer = theme_dark_tertiaryContainer,
    onTertiaryContainer = theme_dark_onTertiaryContainer,
    error = theme_dark_error,
    errorContainer = theme_dark_errorContainer,
    onError = theme_dark_onError,
    onErrorContainer = theme_dark_onErrorContainer,
    background = theme_dark_background,
    onBackground = theme_dark_onBackground,
    surface = theme_dark_surface,
    onSurface = theme_dark_onSurface,
    surfaceVariant = theme_dark_surfaceVariant,
    onSurfaceVariant = theme_dark_onSurfaceVariant,
    outline = theme_dark_outline,
    inverseOnSurface = theme_dark_inverseOnSurface,
    inverseSurface = theme_dark_inverseSurface,
    inversePrimary = theme_dark_inversePrimary,
    outlineVariant = theme_dark_outlineVariant,
    scrim = theme_dark_scrim,
    surfaceBright = theme_dark_surfaceBright,
    surfaceContainer = theme_dark_surfaceContainer,
    surfaceContainerHigh = theme_dark_surfaceContainerHigh,
    surfaceContainerHighest = theme_dark_surfaceContainerHighest,
    surfaceContainerLow = theme_dark_surfaceContainerLow,
    surfaceContainerLowest = theme_dark_surfaceContainerLowest,
    surfaceDim = theme_dark_surfaceDim,
)

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

@Composable
internal fun AppTheme(
    content: @Composable() () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState
    ) {
        val isDark by isDarkState
        SystemAppearance(!isDark)
        MaterialTheme(
            colorScheme = if (isDark) DarkColorScheme else LightColorScheme,
            content = { Surface(content = content) }
        )
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
