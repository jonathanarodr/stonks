package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.repository.StockRepository
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import timber.log.Timber

internal class StockPriceComparatorUseCase(
    private val stockRepository: StockRepository,
) {

    private val stocks: MutableList<StockAlertResponse> = mutableListOf()

    private suspend fun fetchRemoteStock(): MutableList<StockAlertResponse> {
        if (stocks.isEmpty()) {
            stocks.addAll(
                stockRepository.getRemoteStockAlerts().getOrDefault(emptyList())
            )
        }

        return stocks
    }

    private fun searchStockAlert(ticket: String): StockAlertResponse? {
        return stocks.find { it.stockTicket == ticket }?.also {
            Timber.d("Alert found for the stock ${it.stockTicket}")
        }
    }

    private fun calculatePerformance(alertPrice: Double, currentPrice: Double): StockAlertType {
        return if (alertPrice == currentPrice) {
            StockAlertType.UNKNOWN
        } else if (alertPrice < currentPrice) {
            StockAlertType.HIGH_PRICE
        } else {
            StockAlertType.LOW_PRICE
        }
    }

    suspend fun checkStockPrice(alert: StockAlertResponse): StockAlertType {
        fetchRemoteStock()

        return searchStockAlert(alert.stockTicket)?.run {
            calculatePerformance(
                alertPrice = alert.alertValue,
                currentPrice = this.alertValue,
            )
        } ?: StockAlertType.UNKNOWN
    }
}
