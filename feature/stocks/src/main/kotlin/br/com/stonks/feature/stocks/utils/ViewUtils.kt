package br.com.stonks.feature.stocks.utils

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockStatusType

internal fun StockStatusType.getColor(): Color {
    return when (this) {
        StockStatusType.AVAILABLE -> ColorToken.HighlightGreen
        StockStatusType.UNAVAILABLE -> ColorToken.HighlightRed
        StockStatusType.UNKNOWN -> ColorToken.Grayscale200
    }
}

@StringRes
internal fun StockStatusType.getDescription(): Int {
    return when (this) {
        StockStatusType.AVAILABLE -> R.string.status_available
        StockStatusType.UNAVAILABLE -> R.string.status_unavailable
        StockStatusType.UNKNOWN -> R.string.status_unknown
    }
}
