package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.ui.model.AlertUiModel

internal class AlertUiToModelMapper : Mapper<AlertUiModel, StockAlertModel> {

    override fun mapper(input: AlertUiModel) = StockAlertModel(
        id = input.id,
        ticket = input.ticket,
        alertValue = input.alertValue,
        status = input.status,
        notificationTrigger = input.alert,
    )
}
