package br.com.stonks.common.formatters

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class DateFormatterTest {

    @Test
    fun `given date formatter then return value with parametrized pattern`() {
        val result = Date().formatTo(DatePattern.DATE_PATTERN_DD_MMMM_BR)

        assertEquals("03 de maio", result)
    }
}
