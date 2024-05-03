package br.com.stonks.common.formatters

import org.junit.Test
import kotlin.test.assertEquals

class DatePatternTest {

    @Test
    fun `given date pattern DD_MMMM_BR then return valid string`() {
        assertEquals("dd 'de' MMMM", DatePattern.DATE_PATTERN_DD_MMMM_BR)
    }
}
