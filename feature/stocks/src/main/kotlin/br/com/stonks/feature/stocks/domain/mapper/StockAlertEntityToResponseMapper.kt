package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockAlertEntityToResponseMapper : Mapper<StockAlertEntity, StockAlertResponse> {

    override fun mapper(input: StockAlertEntity) = StockAlertResponse(
        id = input.id,
        stockTicket = input.ticket,
        alertValue = input.alertValue,
        status = input.status,
        notificationTrigger = input.notificationTrigger,
    )
}
