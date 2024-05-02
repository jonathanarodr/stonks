package br.com.stonks.feature.home.domain.model

internal data class HomeContentModel(
    val wallet: WalletModel,
    val dailyTransactions: List<DailyTransactionModel>,
)
