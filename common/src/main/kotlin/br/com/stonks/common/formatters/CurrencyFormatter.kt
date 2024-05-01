package br.com.stonks.common.formatters

import java.text.DecimalFormat
import java.util.Locale

fun Double.formatCurrency(locale: Locale = LocaleDefault.localePtBr): String {
    val decimalFormat = DecimalFormat.getCurrencyInstance(locale) as DecimalFormat

    return decimalFormat.run {
        this.format(this@formatCurrency).trim()
    }
}
