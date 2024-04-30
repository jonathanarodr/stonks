package br.com.stonks.feature.home.domain.model

internal data class WalletModel(
    val availableBalance: Double,
    val investedBalance: Double,
    val totalAssets: Double,
    val portfolio: List<PortfolioModel>
)
