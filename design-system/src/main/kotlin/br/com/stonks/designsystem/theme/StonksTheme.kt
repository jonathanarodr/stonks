package br.com.stonks.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.designsystem.tokens.FontSizeToken
import br.com.stonks.designsystem.tokens.ShapeSizeToken

private val ColorScheme = lightColorScheme(
    primary = ColorToken.HighlightRed,
    onPrimary = ColorToken.NeutralWhite,
    background = ColorToken.NeutralBack,
    onBackground = ColorToken.NeutralWhite,
    surface = ColorToken.Grayscale400,
    onSurface = ColorToken.Grayscale100,
    error = ColorToken.HighlightRed,
    onError = ColorToken.NeutralWhite,
)

val ShapeScheme = Shapes(
    small = RoundedCornerShape(ShapeSizeToken.sm),
    medium = RoundedCornerShape(ShapeSizeToken.md),
    large = RoundedCornerShape(ShapeSizeToken.lg)
)

val TypographyScheme = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.xxl,
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.xxl,
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.xxl,
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.xl,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.lg,
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.ExtraBold,
        fontSize = FontSizeToken.md,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeToken.sm,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeToken.xs,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = FontSizeToken.xxs,
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeToken.sm,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeToken.xs,
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeToken.xxs,
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = FontSizeToken.xxs,
    )
)

@Composable
fun StonksTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        shapes = ShapeScheme,
        typography = TypographyScheme,
        content = content
    )
}
