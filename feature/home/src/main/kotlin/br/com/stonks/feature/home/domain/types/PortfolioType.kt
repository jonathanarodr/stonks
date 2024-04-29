package br.com.stonks.feature.home.domain.types

import br.com.stonks.common.utils.safeEnumValueOf

enum class PortfolioType(val id: String) {
    UNKNOWN("unknown"),
    DIGITAL_ACCOUNT("account"),
    INVESTMENT_FUNDS("fund"),
    GOVERNMENT_BONDS("bond"),
    STOCK("stock");

    companion object {

        fun fromString(id: String): PortfolioType {
            return safeEnumValueOf<PortfolioType>(id) ?: UNKNOWN
        }
    }
}
