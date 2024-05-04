package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.repository.local.StockLocalDataSource
import br.com.stonks.feature.stocks.domain.mapper.StockAlertEntityToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToEntityMapper
import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal class StockRepositoryImpl(
    private val stockRemoteDataSource: StockRemoteDataSource,
    private val stockLocalDataSource: StockLocalDataSource,
    private val stockAlertResponseMapper: StockAlertEntityToResponseMapper,
    private val stockAlertEntityMapper: StockAlertResponseToEntityMapper,
) : StockRepository {

    override suspend fun getRemoteStockAlerts(): Result<List<StockAlertResponse>> {
        return stockRemoteDataSource.getStockAlerts()
    }

    override suspend fun listStockAlerts(): Result<List<StockAlertResponse>> {
        return stockLocalDataSource.listStockAlerts().mapCatching { results ->
            results.map {
                stockAlertResponseMapper.mapper(it)
            }
        }
    }

    override suspend fun getStockAlert(alertId: Long): Result<StockAlertResponse> {
        return stockLocalDataSource.getStockAlert(alertId).mapCatching {
            stockAlertResponseMapper.mapper(it)
        }
    }

    override suspend fun insertStockAlert(alert: StockAlertResponse): Result<Unit> {
        return stockAlertEntityMapper.mapper(alert).run {
            stockLocalDataSource.insertStockAlert(this)
        }
    }

    override suspend fun updateStockAlert(alert: StockAlertResponse): Result<Unit> {
        return stockAlertEntityMapper.mapper(alert).run {
            stockLocalDataSource.updateStockAlert(this)
        }
    }

    override suspend fun deleteStockAlert(alertId: Long): Result<Unit> {
        return stockLocalDataSource.deleteStockAlert(alertId)
    }
}
