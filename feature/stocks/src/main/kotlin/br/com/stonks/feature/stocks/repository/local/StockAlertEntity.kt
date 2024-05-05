package br.com.stonks.feature.stocks.repository.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.stonks.common.db.DefaultPrimaryKey

@Entity(tableName = "alerts")
data class StockAlertEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = DefaultPrimaryKey,
    @ColumnInfo(name = "ticket")
    val ticket: String,
    @ColumnInfo(name = "price")
    val alertValue: Double,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "trigger")
    val notificationTrigger: String,
)
