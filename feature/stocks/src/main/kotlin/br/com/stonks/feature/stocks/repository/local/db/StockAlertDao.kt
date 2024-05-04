package br.com.stonks.feature.stocks.repository.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity

@Dao
interface StockAlertDao {

    @Query("select * from alerts order by ticket desc")
    suspend fun listAlerts(): List<StockAlertEntity>

    @Query("select * from alerts where id = :alertId")
    suspend fun selectAlert(alertId: Long): StockAlertEntity

    @Insert
    suspend fun insertAlert(alert: StockAlertEntity)

    @Update
    suspend fun updateAlert(alert: StockAlertEntity)

    @Query("delete from alerts where id = :alertId")
    suspend fun deleteAlert(alertId: Long)
}
