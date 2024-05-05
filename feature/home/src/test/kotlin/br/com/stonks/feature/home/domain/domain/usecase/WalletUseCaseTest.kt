package br.com.stonks.feature.home.domain.domain.usecase

import br.com.stonks.common.exception.StonksApiException
import br.com.stonks.feature.home.domain.mapper.WalletResponseToModelMapper
import br.com.stonks.feature.home.domain.usecase.WalletUseCase
import br.com.stonks.feature.home.repository.HomeRepository
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WalletUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val fakeResponse = mockk<WalletResponse>()
    private val homeRepositoryMock = mockk<HomeRepository>()
    private val walletMapperMock = mockk<WalletResponseToModelMapper>()
    private val sut = WalletUseCase(homeRepositoryMock, walletMapperMock)

    @Test
    fun `given repository when fetch data with success then call mapper`() = runTest {
        val fakeResult = Result.success(fakeResponse)

        coEvery {
            homeRepositoryMock.getWallet()
        } returns fakeResult

        sut()

        verify(exactly = 1) {
            walletMapperMock.mapper(fakeResponse)
        }
    }

    @Test
    fun `given repository when fetch data with exception then do not call mapper`() = runTest {
        coEvery {
            homeRepositoryMock.getWallet()
        } throws StonksApiException("ops", mockk())

        sut()

        verify(exactly = 0) {
            walletMapperMock.mapper(fakeResponse)
        }
    }
}
