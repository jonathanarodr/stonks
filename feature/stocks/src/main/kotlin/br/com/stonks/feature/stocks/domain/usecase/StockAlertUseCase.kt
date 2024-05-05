package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.common.db.DefaultPrimaryKey
import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToModelMapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.repository.StockRepository
import kotlinx.coroutines.flow.Flow

internal class StockAlertUseCase(
    private val stockAlertRepository: StockRepository,
    private val stockAlertModelMapper: StockAlertResponseToModelMapper,
    private val stockAlertResponseMapper: StockAlertModelToResponseMapper,
) {

    suspend fun getRemoteStockAlerts(): Flow<List<StockAlertModel>> {
        return stockAlertRepository.getRemoteStockAlerts().mapCatching {
            stockAlertModelMapper.mapper(it)
        }.asFlow()
    }

    suspend fun listStockAlerts(): Flow<List<StockAlertModel>> {
        return stockAlertRepository.listStockAlerts().mapCatching {
            stockAlertModelMapper.mapper(it)
        }.asFlow()
    }

    suspend fun saveStockAlert(alert: StockAlertModel): Flow<Unit> {
        val alertResponse = stockAlertResponseMapper.mapper(alert)

        return if (alert.id == DefaultPrimaryKey) {
            stockAlertRepository.insertStockAlert(alertResponse)
        } else {
            stockAlertRepository.updateStockAlert(alertResponse)
        }.asFlow()
    }

    suspend fun deleteStockAlert(alertId: Long): Flow<Unit> {
        return stockAlertRepository.deleteStockAlert(alertId).asFlow()
    }
}
