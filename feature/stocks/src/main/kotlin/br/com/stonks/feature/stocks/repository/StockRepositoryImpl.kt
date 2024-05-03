package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.repository.local.StockLocalDataSource
import br.com.stonks.feature.stocks.repository.mapper.StockAlertEntityToResponseMapper
import br.com.stonks.feature.stocks.repository.mapper.StockAlertResponseToEntityMapper
import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockRepositoryImpl(
    private val stockRemoteDataSource: StockRemoteDataSource,
    private val stockLocalDataSource: StockLocalDataSource,
    private val stockAlertEntityToResponseMapper: StockAlertEntityToResponseMapper,
    private val stockAlertResponseToEntityMapper: StockAlertResponseToEntityMapper,
) : StockRepository {

    override suspend fun listStockAlerts(): Result<List<StockAlertResponse>> {
        return stockRemoteDataSource.getStockAlerts().onSuccess { result ->
            result.forEach {
                stockLocalDataSource.insertStockAlert(
                    entity = stockAlertResponseToEntityMapper.mapper(it),
                )
            }
        }
    }

    override suspend fun getStockAlert(alertId: Long): Result<StockAlertResponse> {
        return stockLocalDataSource.getStockAlert(alertId).mapCatching {
            stockAlertEntityToResponseMapper.mapper(it)
        }
    }

    override suspend fun insertStockAlert(alert: StockAlertResponse): Result<Unit> {
        return stockAlertResponseToEntityMapper.mapper(alert).run {
            stockLocalDataSource.insertStockAlert(this)
        }
    }

    override suspend fun deleteStockAlert(alert: StockAlertResponse): Result<Unit> {
        return stockAlertResponseToEntityMapper.mapper(alert).run {
            stockLocalDataSource.deleteStockAlert(this)
        }
    }
}
