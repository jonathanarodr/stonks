package br.com.stonks.feature.home.domain.domain.types

import br.com.stonks.feature.home.domain.types.TransactionType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class TransactionTypeTest(
    private val input: String,
    private val expected: TransactionType,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun arguments(): List<Array<Any>> = listOf(
            arrayOf("unknown", TransactionType.UNKNOWN),
            arrayOf("expense", TransactionType.EXPENSE),
            arrayOf("income", TransactionType.INCOME),
        )
    }

    @Test
    fun `given type string when convert to enum return a mapped type`() {
        assertEquals(expected, TransactionType.fromString(input))
    }
}
