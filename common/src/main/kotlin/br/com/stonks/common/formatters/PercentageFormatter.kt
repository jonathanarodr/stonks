package br.com.stonks.common.formatters

const val PERCENT_MULTIPLIER = 100

fun Float.formatPercent(): String {
    return (this * PERCENT_MULTIPLIER).run {
        "%.2f%%".format(this)
    }
}
