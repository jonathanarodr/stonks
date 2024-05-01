package br.com.stonks.common.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val DATE_PATTERN_DD_MMMM_BR = "dd 'de' MMMM"

fun Date.formatTo(pattern: String, locale: Locale = LocaleDefault.localePtBr): String {
    return SimpleDateFormat(pattern, locale).format(this)
}
