package br.com.stonks.feature.home.repository

import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryImplTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val homeRemoteDataSourceMock = mockk<HomeRemoteDataSource>()
    private val sut = HomeRepositoryImpl(homeRemoteDataSourceMock)

    @Test
    fun `given repository when call wallet remote source then return result data`() = runTest {
        val expected = Result.success(mockk<WalletResponse>())

        coEvery {
            homeRemoteDataSourceMock.getWallet()
        } returns expected

        val result = sut.getWallet()

        coVerify {
            homeRemoteDataSourceMock.getWallet()
        }

        assertEquals(expected, result)
    }

    @Test
    fun `given repository when call transaction remote source then return result data`() = runTest {
        val expected = Result.success(mockk<List<DailyTransactionResponse>>())

        coEvery {
            homeRemoteDataSourceMock.getTransactions()
        } returns expected

        val result = sut.getTransactions()

        coVerify {
            homeRemoteDataSourceMock.getTransactions()
        }

        assertEquals(expected, result)
    }
}
