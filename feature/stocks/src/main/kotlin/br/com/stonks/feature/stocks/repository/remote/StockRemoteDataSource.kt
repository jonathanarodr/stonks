package br.com.stonks.feature.stocks.repository.remote

import br.com.stonks.common.executors.ApiExecutor
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockRemoteDataSource(
    private val stockApiService: StockApiService,
) : ApiExecutor() {

    suspend fun getStockAlerts(): Result<List<StockAlertResponse>> = execute {
        stockApiService.getStockAlerts()
    }
}
