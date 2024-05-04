package br.com.stonks.feature.stocks.repository.local

import br.com.stonks.common.executors.StorageExecutor
import br.com.stonks.feature.stocks.repository.local.db.StockAlertDao

class StockLocalDataSource(
    private val stockAlertDao: StockAlertDao,
) : StorageExecutor() {

    suspend fun listStockAlerts(): Result<List<StockAlertEntity>> = execute {
        stockAlertDao.listAlerts()
    }

    suspend fun getStockAlert(alertId: Long): Result<StockAlertEntity> = execute {
        stockAlertDao.selectAlert(alertId)
    }

    suspend fun insertStockAlert(entity: StockAlertEntity): Result<Unit> = execute {
        stockAlertDao.insertAlert(entity)
    }

    suspend fun updateStockAlert(entity: StockAlertEntity): Result<Unit> = execute {
        stockAlertDao.updateAlert(entity)
    }

    suspend fun deleteStockAlert(alertId: Long): Result<Unit> = execute {
        stockAlertDao.deleteAlert(alertId)
    }
}
