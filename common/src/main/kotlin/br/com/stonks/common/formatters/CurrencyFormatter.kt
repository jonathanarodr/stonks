package br.com.stonks.common.formatters

import androidx.core.text.isDigitsOnly
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.Locale

private const val CurrencyDivider = 100

fun Double.formatCurrency(locale: Locale = LocaleDefault.localePtBr): String {
    val decimalFormat = DecimalFormat.getCurrencyInstance(locale) as DecimalFormat

    return decimalFormat.run {
        this.format(this@formatCurrency).trim()
    }
}

fun String.toCurrency(): Double {
    val divider: BigDecimal by lazy { BigDecimal(CurrencyDivider) }
    val onlyNumbers = if (this.isDigitsOnly()) {
        this
    } else {
        "[^0-9]".toRegex().replace(this, "")
    }

    return (
        onlyNumbers.toBigDecimalOrNull()?.run {
            this.divide(divider).setScale(2, RoundingMode.HALF_DOWN)
        } ?: BigDecimal.ZERO
        ).toDouble()
}
