package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.feature.home.domain.model.PortfolioModel
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.domain.types.PortfolioType
import br.com.stonks.feature.home.repository.remote.response.PortfolioResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import org.junit.Test
import kotlin.test.assertEquals

class WalletResponseToModelMapperTest {

    private val sut = WalletResponseToModelMapper()

    @Test
    fun `given response data when call mapper then convert to model`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = WalletResponse(
        availableBalance = 1.0,
        investedBalance = 1.0,
        totalAssets = 2.0,
        portfolio = listOf(
            PortfolioResponse(
                portfolioName = "lorem",
                portfolioType = "account",
                totalInvestment = 1.0,
                allocation = 1f,
            )
        )
    )

    private fun createExpectedData() = WalletModel(
        availableBalance = 1.0,
        investedBalance = 1.0,
        totalAssets = 2.0,
        portfolio = listOf(
            PortfolioModel(
                portfolioName = "lorem",
                portfolioType = PortfolioType.DIGITAL_ACCOUNT,
                totalInvestment = 1.0,
                allocation = 1f,
            )
        )
    )
}
