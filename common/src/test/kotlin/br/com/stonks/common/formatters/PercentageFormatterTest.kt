package br.com.stonks.common.formatters

import org.junit.Assert.assertEquals
import org.junit.Test

class PercentageFormatterTest {

    @Test
    fun `given percentage formatter then return format string`() {
        val result = 0.7343f.formatPercent()

        assertEquals("73.43%", result)
    }
}
