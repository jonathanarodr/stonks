package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockAlertResponseToModelMapper : Mapper<StockAlertResponse, StockAlertModel> {

    override fun mapper(input: StockAlertResponse): StockAlertModel {
        return mapperStockAlert(input)
    }

    private fun mapperStockAlert(input: StockAlertResponse) = StockAlertModel(
        id = input.id,
        ticket = input.stockTicket,
        alertValue = input.alertValue,
        status = StockStatusType.fromString(input.status),
        notificationTrigger = StockAlertType.fromString(input.notificationTrigger),
    )
}
