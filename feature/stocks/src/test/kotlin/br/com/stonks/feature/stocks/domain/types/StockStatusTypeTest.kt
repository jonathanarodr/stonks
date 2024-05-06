package br.com.stonks.feature.stocks.domain.types

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class StockStatusTypeTest(
    private val input: String,
    private val expected: StockStatusType,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun arguments(): List<Array<Any>> = listOf(
            arrayOf("unknown", StockStatusType.UNKNOWN),
            arrayOf("available", StockStatusType.AVAILABLE),
            arrayOf("unavailable", StockStatusType.UNAVAILABLE),
        )
    }

    @Test
    fun `given type string when convert to enum return a mapped type`() {
        assertEquals(expected, StockStatusType.fromString(input))
    }
}
