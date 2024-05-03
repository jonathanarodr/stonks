package br.com.stonks.feature.home.domain.domain.types

import br.com.stonks.feature.home.domain.types.PortfolioType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class PortfolioTypeTest(
    private val input: String,
    private val expected: PortfolioType,
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun arguments(): List<Array<Any>> = listOf(
            arrayOf("unknown", PortfolioType.UNKNOWN),
            arrayOf("account", PortfolioType.DIGITAL_ACCOUNT),
            arrayOf("fund", PortfolioType.INVESTMENT_FUNDS),
            arrayOf("bond", PortfolioType.GOVERNMENT_BONDS),
            arrayOf("stock", PortfolioType.STOCK),
            arrayOf("lorem", PortfolioType.UNKNOWN),
        )
    }

    @Test
    fun `given type string when convert to enum return a mapped type`() {
        assertEquals(expected, PortfolioType.fromString(input))
    }
}
