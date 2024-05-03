package br.com.stonks.feature.stocks.domain.types

import timber.log.Timber

internal enum class StockStatusType(val status: String) {
    UNKNOWN("unknown"),
    AVAILABLE("available"),
    UNAVAILABLE("unavailable");

    companion object {

        fun fromString(id: String): StockStatusType {
            return try {
                enumValues<StockStatusType>().find { it.status == id } ?: UNKNOWN
            } catch (exception: IllegalArgumentException) {
                Timber.e(exception, "Failure to find the enum value with status '$id'")
                UNKNOWN
            }
        }
    }
}
