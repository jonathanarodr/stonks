package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockAlertResponseToEntityMapper : Mapper<StockAlertResponse, StockAlertEntity> {

    override fun mapper(input: StockAlertResponse) = StockAlertEntity(
        id = input.id,
        ticket = input.stockTicket,
        alertValue = input.alertValue,
        status = input.status,
        notificationTrigger = input.notificationTrigger,
    )
}
