package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse

internal interface StockRepository {

    suspend fun getRemoteStockAlerts(): Result<List<StockAlertResponse>>

    suspend fun listStockAlerts(): Result<List<StockAlertResponse>>

    suspend fun getStockAlert(alertId: Long): Result<StockAlertResponse>

    suspend fun insertStockAlert(alert: StockAlertResponse): Result<Unit>

    suspend fun updateStockAlert(alert: StockAlertResponse): Result<Unit>

    suspend fun deleteStockAlert(alertId: Long): Result<Unit>
}
