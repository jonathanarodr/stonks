package br.com.stonks.feature.stocks.domain.types

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class StockAlertTypeTest(
    private val input: String,
    private val expected: StockAlertType,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun arguments(): List<Array<Any>> = listOf(
            arrayOf("unknown", StockAlertType.UNKNOWN),
            arrayOf("high", StockAlertType.HIGH_PRICE),
            arrayOf("low", StockAlertType.LOW_PRICE),
        )
    }

    @Test
    fun `given type string when convert to enum return a mapped type`() {
        Assert.assertEquals(expected, StockAlertType.fromString(input))
    }
}
