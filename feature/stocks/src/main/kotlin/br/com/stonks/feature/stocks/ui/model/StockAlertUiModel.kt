package br.com.stonks.feature.stocks.ui.model

import androidx.compose.ui.graphics.Color
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType

internal data class AlertUiModel(
    val id: Long,
    val ticket: String,
    val alertValue: Double,
    val status: StockStatusType,
    val alert: StockAlertType = StockAlertType.UNKNOWN,
    val tagColor: Color = ColorToken.Grayscale200,
)

internal data class StockAlertUiModel(
    val totalAssets: Double,
    val stockAlerts: List<AlertUiModel>,
)
