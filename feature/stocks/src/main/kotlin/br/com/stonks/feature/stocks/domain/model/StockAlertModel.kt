package br.com.stonks.feature.stocks.domain.model

import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType

internal data class StockAlertModel(
    val id: Long,
    val ticket: String,
    val alertValue: Double,
    val status: StockStatusType,
    val notificationTrigger: StockAlertType,
)
