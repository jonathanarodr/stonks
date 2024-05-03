package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.stocks.domain.mapper.StockAlertMapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.repository.StockRepository
import kotlinx.coroutines.flow.Flow

internal class StockAlertUseCase(
    private val stockAlertRepository: StockRepository,
    private val stockAlertMapper: StockAlertMapper,
) {

    suspend fun fetchData(): Flow<List<StockAlertModel>> {
        return stockAlertRepository.getStockAlerts().mapCatching {
            stockAlertMapper.mapper(it)
        }.asFlow()
    }
}
