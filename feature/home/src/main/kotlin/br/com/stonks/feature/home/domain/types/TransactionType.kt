package br.com.stonks.feature.home.domain.types

import br.com.stonks.common.utils.safeEnumValueOf

enum class TransactionType(val id: String) {
    UNKNOWN("unknown"),
    EXPENSIVE("expensive"),
    INCOME("income");

    companion object {

        fun fromString(id: String): TransactionType {
            return safeEnumValueOf<TransactionType>(id) ?: UNKNOWN
        }
    }
}
