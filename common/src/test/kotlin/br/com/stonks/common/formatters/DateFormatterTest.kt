package br.com.stonks.common.formatters

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class DateFormatterTest {

    private val currentDate = Date(2024, 4, 3)

    @Test
    fun `given date formatter then return value with parametrized pattern`() {
        val result = currentDate.formatTo(DatePattern.DATE_PATTERN_DD_MMMM_BR)

        assertEquals("03 de maio", result)
    }
}
