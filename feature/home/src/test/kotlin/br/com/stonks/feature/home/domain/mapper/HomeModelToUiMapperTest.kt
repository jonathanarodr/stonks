package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.designsystem.components.PieChartData
import br.com.stonks.designsystem.components.PieChartDataProgress
import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.domain.model.HomeContentModel
import br.com.stonks.feature.home.domain.model.PortfolioModel
import br.com.stonks.feature.home.domain.model.TransactionModel
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.domain.types.PortfolioType
import br.com.stonks.feature.home.domain.types.TransactionType
import br.com.stonks.feature.home.ui.model.DailyTransactionUiModel
import br.com.stonks.feature.home.ui.model.HomeUiModel
import br.com.stonks.feature.home.ui.model.PortfolioUiModel
import br.com.stonks.feature.home.ui.model.TransactionUiModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class HomeModelToUiMapperTest {

    private val currentDate = Date(2024, 4, 3)
    private val walletModelToChartMapper = mockk<WalletModelToChartMapper>()
    private val sut = HomeModelToUiMapper(walletModelToChartMapper)

    @Test
    fun `given model when call mapper then convert to ui model`() {
        every {
            walletModelToChartMapper.mapper(any())
        } returns createPieChartData()

        val expected = createResultData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createPieChartData() = listOf(
        PieChartData(
            title = "Todos os produtos",
            value = 1.0,
            progress = 1f,
            dataProgress = listOf(
                PieChartDataProgress(
                    progress = 0.5f,
                    progressColor = ColorToken.HighlightOrange,
                ),
            ),
        ),
    )

    private fun createResultData() = HomeUiModel(
        totalAssets = 1.0,
        portfolioChart = createPieChartData(),
        portfolio = listOf(
            PortfolioUiModel(
                tagColor = ColorToken.HighlightOrange,
                portfolioName = "lorem",
                totalInvestment = 1.0,
                allocation = 0.5f,
            ),
        ),
        dailyTransactions = listOf(
            DailyTransactionUiModel(
                dateGroup = "03 de maio",
                dailyBalance = 1.0,
                transactions = listOf(
                    TransactionUiModel(
                        icon = br.com.stonks.designsystem.R.drawable.ic_income,
                        description = "ipsum",
                        value = 5.0,
                    ),
                ),
            ),
        ),
    )

    private fun createInputData() = HomeContentModel(
        wallet = WalletModel(
            availableBalance = 1.0,
            investedBalance = 1.0,
            totalAssets = 1.0,
            portfolio = listOf(
                PortfolioModel(
                    portfolioName = "lorem",
                    portfolioType = PortfolioType.DIGITAL_ACCOUNT,
                    totalInvestment = 1.0,
                    allocation = 0.5f,
                )
            ),
        ),
        dailyTransactions = listOf(
            DailyTransactionModel(
                date = currentDate,
                dailyBalance = 1.0,
                transactions = listOf(
                    TransactionModel(
                        type = TransactionType.INCOME,
                        description = "ipsum",
                        value = 5.0,
                    ),
                )
            ),
        ),
    )
}
