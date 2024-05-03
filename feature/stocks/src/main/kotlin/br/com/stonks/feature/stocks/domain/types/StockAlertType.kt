package br.com.stonks.feature.stocks.domain.types

import timber.log.Timber

internal enum class StockAlertType(val status: String) {
    UNKNOWN("unknown"),
    HIGH_PRICE("high"),
    LOW_PRICE("low");

    companion object {

        fun fromString(id: String): StockAlertType {
            return try {
                enumValues<StockAlertType>().find { it.status == id } ?: UNKNOWN
            } catch (exception: IllegalArgumentException) {
                Timber.e(exception, "Failure to find the enum value with status '$id'")
                UNKNOWN
            }
        }
    }
}
