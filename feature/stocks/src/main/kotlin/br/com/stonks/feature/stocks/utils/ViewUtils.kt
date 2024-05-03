package br.com.stonks.feature.stocks.utils

import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.stocks.domain.types.StockStatusType

internal fun StockStatusType.getColor(): Color {
    return when (this) {
        StockStatusType.AVAILABLE -> ColorToken.HighlightGreen
        StockStatusType.UNAVAILABLE -> ColorToken.HighlightRed
        else -> ColorToken.Grayscale200
    }
}
