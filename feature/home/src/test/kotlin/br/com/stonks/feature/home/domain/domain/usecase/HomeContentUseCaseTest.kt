package br.com.stonks.feature.home.domain.domain.usecase

import app.cash.turbine.test
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.domain.model.HomeContentModel
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.domain.usecase.DailyTransactionUseCase
import br.com.stonks.feature.home.domain.usecase.HomeContentUseCase
import br.com.stonks.feature.home.domain.usecase.WalletUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class HomeContentUseCaseTest {

    private val currentDate = Date()
    private val walletUseCaseMock = mockk<WalletUseCase>()
    private val dailyTransactionUseCaseMock = mockk<DailyTransactionUseCase>()
    private val sut = HomeContentUseCase(walletUseCaseMock, dailyTransactionUseCaseMock)

    @Test
    fun `given use case when fetch data with success then return valid result`() = runTest {
        coEvery { walletUseCaseMock() } returns flowOf(fakeWallet)
        coEvery { dailyTransactionUseCaseMock() } returns flowOf(fakeTransaction)

        sut.fetchData().test {
            assertEquals(fakeHomeContent(), awaitItem())
            awaitComplete()
        }
    }

    private fun fakeHomeContent() = HomeContentModel(
        wallet = fakeWallet,
        dailyTransactions = fakeTransaction,
    )

    private val fakeWallet = WalletModel(
        availableBalance = 1.0,
        investedBalance = 1.0,
        totalAssets = 2.0,
        portfolio = emptyList(),
    )

    private val fakeTransaction = listOf(
        DailyTransactionModel(
            date = currentDate,
            dailyBalance = 1.0,
            transactions = emptyList(),
        )
    )
}
