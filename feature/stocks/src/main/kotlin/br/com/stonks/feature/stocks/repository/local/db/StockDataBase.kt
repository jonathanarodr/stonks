package br.com.stonks.feature.stocks.repository.local.db

import android.app.Application
import androidx.room.Database
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.stonks.common.db.DateConverter
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity

@Database(
    version = 1,
    entities = [StockAlertEntity::class],
    exportSchema = false,
)
@TypeConverters(
    DateConverter::class,
)
@RewriteQueriesToDropUnusedColumns
abstract class StockDataBase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "stock"

        operator fun invoke(context: Application): StockDataBase {
            return Room.databaseBuilder(
                context,
                StockDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }

    abstract fun stockAlertDao(): StockAlertDao
}
