package com.example.wildwaysenjoyer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Sand,
    onPrimary = Graphite,
    primaryContainer = GraphiteVariant,
    onPrimaryContainer = PaperDark,
    secondary = MossSoft,
    onSecondary = Graphite,
    secondaryContainer = GraphiteSurface,
    onSecondaryContainer = PaperDark,
    tertiary = AmberSoft,
    onTertiary = Graphite,
    tertiaryContainer = Color(0xFF4B2B1C),
    onTertiaryContainer = PaperDark,
    background = Graphite,
    onBackground = PaperDark,
    surface = GraphiteSurface,
    onSurface = PaperDark,
    surfaceVariant = GraphiteVariant,
    onSurfaceVariant = SandSoft,
    outline = Color(0xFF8E7A71)
)

private val LightColorScheme = lightColorScheme(
    primary = Ember,
    onPrimary = Color.White,
    primaryContainer = EmberSoft,
    onPrimaryContainer = Color(0xFF34110A),
    secondary = Moss,
    onSecondary = Color.White,
    secondaryContainer = MossSoft,
    onSecondaryContainer = Color(0xFF132010),
    tertiary = Amber,
    onTertiary = Color.White,
    tertiaryContainer = AmberSoft,
    onTertiaryContainer = Color(0xFF341405),
    background = Paper,
    onBackground = Charcoal,
    surface = Color.White,
    onSurface = Charcoal,
    surfaceVariant = Color(0xFFE8DDD5),
    onSurfaceVariant = CharcoalSoft,
    outline = Color(0xFF87766E)
)

@Composable
fun WildwaysEnjoyerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
