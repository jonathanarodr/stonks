package br.com.stonks.common.formatters

private const val PercentMultiplier = 100

fun Float.formatPercent(): String {
    return (this * PercentMultiplier).run {
        "%.2f%%".format(this)
    }
}
