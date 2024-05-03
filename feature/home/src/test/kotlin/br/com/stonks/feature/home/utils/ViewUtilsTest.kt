package br.com.stonks.feature.home.utils

import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.home.domain.types.PortfolioType
import br.com.stonks.feature.home.domain.types.TransactionType
import org.junit.Assert.assertEquals
import org.junit.Test

class ViewUtilsTest {

    @Test
    fun `given digital account portfolio type then return a valid color`() {
        assertEquals(ColorToken.HighlightOrange, PortfolioType.DIGITAL_ACCOUNT.getColor())
    }

    @Test
    fun `given funds portfolio type then return a valid color`() {
        assertEquals(ColorToken.HighlightBlue, PortfolioType.INVESTMENT_FUNDS.getColor())
    }

    @Test
    fun `given bonds portfolio type then return a valid color`() {
        assertEquals(ColorToken.HighlightPurple, PortfolioType.GOVERNMENT_BONDS.getColor())
    }

    @Test
    fun `given stock portfolio type then return a valid color`() {
        assertEquals(ColorToken.HighlightGreen, PortfolioType.STOCK.getColor())
    }

    @Test
    fun `given unknown portfolio type then return default color`() {
        assertEquals(ColorToken.Grayscale200, PortfolioType.UNKNOWN.getColor())
    }

    @Test
    fun `given income transaction type then return a valid icon`() {
        assertEquals(br.com.stonks.designsystem.R.drawable.ic_income, TransactionType.INCOME.getIcon())
    }

    @Test
    fun `given expense transaction type then return a valid icon`() {
        assertEquals(br.com.stonks.designsystem.R.drawable.ic_expense, TransactionType.EXPENSE.getIcon())
    }

    @Test
    fun `given unknown transaction type then return default icon`() {
        assertEquals(br.com.stonks.designsystem.R.drawable.ic_more, TransactionType.UNKNOWN.getIcon())
    }
}
