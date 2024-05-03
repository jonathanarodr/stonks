package br.com.stonks.feature.home.repository.remote

import br.com.stonks.common.exception.StonksApiException
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRemoteDataSourceTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val homeApiServiceMock = mockk<HomeApiService>()
    private val sut = HomeRemoteDataSource(homeApiServiceMock)

    @Test
    fun `given remote source when call wallet service then return result data`() = runTest {
        val expected = mockk<WalletResponse>()

        coEvery {
            homeApiServiceMock.getWallet()
        } returns expected

        val result = sut.getWallet()

        coVerify {
            homeApiServiceMock.getWallet()
        }

        assertEquals(Result.success(expected), result)
    }

    @Test
    fun `given remote source when call wallet with exception then return failure result data`() = runTest {
        coEvery {
            homeApiServiceMock.getWallet()
        } throws StonksApiException("ops", mockk())

        val result = sut.getWallet()

        coVerify {
            homeApiServiceMock.getWallet()
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun `given remote source when call transactions service then return result data`() = runTest {
        val expected = mockk<List<DailyTransactionResponse>>()

        coEvery {
            homeApiServiceMock.getTransactions()
        } returns expected

        val result = sut.getTransactions()

        coVerify {
            homeApiServiceMock.getTransactions()
        }

        assertEquals(Result.success(expected), result)
    }

    @Test
    fun `given remote source when call transactions with exception then return failure result data`() = runTest {
        coEvery {
            homeApiServiceMock.getTransactions()
        } throws StonksApiException("ops", mockk())

        val result = sut.getTransactions()

        coVerify {
            homeApiServiceMock.getTransactions()
        }

        assertTrue(result.isFailure)
    }
}
