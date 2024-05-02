package br.com.stonks.feature.home.domain.types

import timber.log.Timber

enum class TransactionType(val id: String) {
    UNKNOWN("unknown"),
    EXPENSE("expense"),
    INCOME("income");

    companion object {

        fun fromString(id: String): TransactionType {
            return try {
                enumValues<TransactionType>().find { it.id == id } ?: UNKNOWN
            } catch (exception: IllegalArgumentException) {
                Timber.e(exception, "Failure to find the enum value with ID '$id'")
                UNKNOWN
            }
        }
    }
}
