package br.com.stonks.feature.home.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.components.PieChartData

internal data class PortfolioUiModel(
    val tagColor: Color,
    val portfolioName: String,
    val totalInvestment: Double,
    val allocation: Float,
)

internal data class TransactionUiModel(
    @DrawableRes val icon: Int,
    val description: String,
    val value: Double,
)

internal data class DailyTransactionUiModel(
    val dateGroup: String,
    val dailyBalance: Double,
    val transactions: List<TransactionUiModel>,
)

internal data class HomeUiModel(
    val totalAssets: Double,
    val portfolioChart: PieChartData,
    val portfolio: List<PortfolioUiModel>,
    val dailyTransactions: List<DailyTransactionUiModel>,
)
