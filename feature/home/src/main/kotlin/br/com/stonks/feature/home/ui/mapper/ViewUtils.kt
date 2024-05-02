package br.com.stonks.feature.home.ui.mapper

import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.home.domain.types.PortfolioType
import br.com.stonks.feature.home.domain.types.TransactionType

fun PortfolioType.getColor(): Color {
    return when (this) {
        PortfolioType.DIGITAL_ACCOUNT -> ColorToken.HighlightOrange
        PortfolioType.INVESTMENT_FUNDS -> ColorToken.HighlightBlue
        PortfolioType.GOVERNMENT_BONDS -> ColorToken.HighlightPurple
        PortfolioType.STOCK -> ColorToken.HighlightGreen
        else -> ColorToken.Grayscale200
    }
}

fun TransactionType.getIcon(): Int {
    return when (this) {
        TransactionType.INCOME -> br.com.stonks.designsystem.R.drawable.ic_income
        TransactionType.EXPENSIVE -> br.com.stonks.designsystem.R.drawable.ic_expensive
        TransactionType.UNKNOWN -> br.com.stonks.designsystem.R.drawable.ic_more
    }
}
