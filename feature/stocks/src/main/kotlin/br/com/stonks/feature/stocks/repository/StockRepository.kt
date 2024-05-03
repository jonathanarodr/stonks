package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal interface StockRepository {

    suspend fun getStockAlerts(): Result<List<StockAlertResponse>>
}
