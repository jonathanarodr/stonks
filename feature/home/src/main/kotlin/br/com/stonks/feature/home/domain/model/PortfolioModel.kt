package br.com.stonks.feature.home.domain.model

import br.com.stonks.feature.home.domain.types.PortfolioType

internal data class PortfolioModel(
    val portfolioName: String,
    val portfolioType: PortfolioType,
    val totalInvestment: Double,
    val allocation: Double,
)
