package br.com.stonks.feature.stocks.repository.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity

@Dao
interface StockAlertDao {

    @Query("select * from alerts order by ticket desc")
    suspend fun listAlerts(): List<StockAlertEntity>

    @Query("select * from alerts where id = :alertId")
    suspend fun selectAlert(alertId: Long): StockAlertEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlert(alert: StockAlertEntity)

    @Delete
    suspend fun deleteAlert(alert: StockAlertEntity)
}
