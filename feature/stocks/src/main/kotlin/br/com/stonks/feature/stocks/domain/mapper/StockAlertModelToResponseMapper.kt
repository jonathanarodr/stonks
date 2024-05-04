package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockAlertModelToResponseMapper : Mapper<StockAlertModel, StockAlertResponse> {

    override fun mapper(input: StockAlertModel) = StockAlertResponse(
        id = input.id,
        stockTicket = input.ticket,
        alertValue = input.alertValue,
        status = input.status.status,
        notificationTrigger = input.notificationTrigger.status,
    )
}
