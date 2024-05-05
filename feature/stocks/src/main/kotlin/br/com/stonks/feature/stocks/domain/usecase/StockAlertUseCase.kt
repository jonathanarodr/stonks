package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.common.db.DefaultPrimaryKey
import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToModelMapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.repository.StockRepository
import kotlinx.coroutines.flow.Flow

internal class StockAlertUseCase(
    private val stockRepository: StockRepository,
    private val stockPriceComparatorUseCase: StockPriceComparatorUseCase,
    private val stockAlertModelMapper: StockAlertResponseToModelMapper,
    private val stockAlertResponseMapper: StockAlertModelToResponseMapper,
) {

    suspend fun listStockAlerts(): Flow<List<StockAlertModel>> {
        return stockRepository.listStockAlerts().mapCatching { results ->
            results.map {
                val alertType = stockPriceComparatorUseCase.checkStockPrice(it)
                stockAlertModelMapper.mapper(it.copy(notificationTrigger = alertType.status))
            }
        }.asFlow()
    }

    suspend fun saveStockAlert(alert: StockAlertModel): Flow<Unit> {
        val alertResponse = stockAlertResponseMapper.mapper(alert)

        return if (alert.id == DefaultPrimaryKey) {
            stockRepository.insertStockAlert(alertResponse)
        } else {
            stockRepository.updateStockAlert(alertResponse)
        }.asFlow()
    }

    suspend fun deleteStockAlert(alertId: Long): Flow<Unit> {
        return stockRepository.deleteStockAlert(alertId).asFlow()
    }
}
