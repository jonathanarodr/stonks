package br.com.stonks.feature.stocks.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.stocks.R
import br.com.stonks.feature.stocks.domain.types.StockAlertType
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

internal fun StockAlertType.getColor(): Color {
    return when (this) {
        StockAlertType.HIGH_PRICE -> ColorToken.HighlightGreen
        StockAlertType.LOW_PRICE -> ColorToken.HighlightRed
        StockAlertType.UNKNOWN -> ColorToken.Grayscale200
    }
}

@DrawableRes
internal fun StockAlertType.getIcon(): Int {
    return when (this) {
        StockAlertType.HIGH_PRICE -> br.com.stonks.designsystem.R.drawable.ic_arrow_up
        StockAlertType.LOW_PRICE -> br.com.stonks.designsystem.R.drawable.ic_arrow_down
        StockAlertType.UNKNOWN -> br.com.stonks.designsystem.R.drawable.ic_remove
    }
}
