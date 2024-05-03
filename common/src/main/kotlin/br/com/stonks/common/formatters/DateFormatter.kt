package br.com.stonks.common.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatTo(pattern: String, locale: Locale = LocaleDefault.localePtBr): String {
    return SimpleDateFormat(pattern, locale).format(this)
}
