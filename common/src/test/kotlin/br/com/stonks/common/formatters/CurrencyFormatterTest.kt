package br.com.stonks.common.formatters

import org.junit.Test
import kotlin.test.assertEquals

class CurrencyFormatterTest {

    @Test
    fun `given currency formatter then return value with parametrized locale`() {
        val result = 16.00.formatCurrency()

        assertEquals("R\$ 16,00", result)
    }
}
