package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockRepositoryImpl(
    private val stockRemoteDataSource: StockRemoteDataSource,
) : StockRepository {

    override suspend fun getStockAlerts(): Result<List<StockAlertResponse>> {
        return stockRemoteDataSource.getStockAlerts()
    }
}
