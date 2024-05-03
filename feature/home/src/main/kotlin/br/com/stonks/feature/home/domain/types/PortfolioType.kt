package br.com.stonks.feature.home.domain.types

import timber.log.Timber

internal enum class PortfolioType(val id: String) {
    UNKNOWN("unknown"),
    DIGITAL_ACCOUNT("account"),
    INVESTMENT_FUNDS("fund"),
    GOVERNMENT_BONDS("bond"),
    STOCK("stock");

    companion object {

        fun fromString(id: String): PortfolioType {
            return try {
                enumValues<PortfolioType>().find { it.id == id } ?: UNKNOWN
            } catch (exception: IllegalArgumentException) {
                Timber.e(exception, "Failure to find the enum value with ID '$id'")
                UNKNOWN
            }
        }
    }
}
