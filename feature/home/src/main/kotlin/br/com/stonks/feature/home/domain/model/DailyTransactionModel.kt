package br.com.stonks.feature.home.domain.model

import java.util.Date

internal data class DailyTransactionModel(
    val date: Date,
    val dailyBalance: Double,
    val transactions: List<TransactionModel>,
)
