package br.com.stonks.common.formatters

import org.junit.Assert.assertEquals
import org.junit.Test

class CurrencyFormatterTest {

    private val delta = 0.0

    @Test
    fun `given currency formatter then return value with parametrized locale`() {
        val result = 16.00.formatCurrency()

        assertEquals("R\$ 16,00", result)
    }

    @Test
    fun `given currency formatter when convert invalid value then return default zero`() {
        assertEquals(0.0, "lorem".toCurrency(), delta)
    }

    @Test
    fun `given currency formatter when convert with partially invalid value then return only numbers`() {
        assertEquals(0.14, "14B".toCurrency(), delta)
    }

    @Test
    fun `given currency formatter when convert value with symbol then return currency value`() {
        assertEquals(0.14, "R$ 0,14".toCurrency(), delta)
    }

    @Test
    fun `given currency formatter when convert value with format numbers then return currency value`() {
        assertEquals(0.14, "0,14".toCurrency(), delta)
    }

    @Test
    fun `given currency formatter when convert value with only numbers then return currency value`() {
        assertEquals(0.14, "14".toCurrency(), delta)
    }
}
