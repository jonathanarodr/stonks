package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.ui.model.StockAlertUiModel
import br.com.stonks.feature.stocks.utils.getColor

internal class StockAlertModelToUiMapper : Mapper<List<StockAlertModel>, StockAlertUiModel> {

    override fun mapper(input: List<StockAlertModel>) = StockAlertUiModel(
        totalAssets = input.sumOf { it.alertValue },
        stockAlerts = input.map(::mapperStockAlert),
    )

    private fun mapperStockAlert(input: StockAlertModel) = AlertUiModel(
        id = input.id,
        ticket = input.ticket,
        alertValue = input.alertValue,
        status = input.status,
        alert = input.notificationTrigger,
        tagColor = input.status.getColor(),
    )
}
