package br.com.stonks.feature.home.domain.domain.usecase

import br.com.stonks.common.exception.StonksApiException
import br.com.stonks.feature.home.domain.mapper.DailyTransactionMapper
import br.com.stonks.feature.home.domain.usecase.DailyTransactionUseCase
import br.com.stonks.feature.home.repository.HomeRepository
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DailyTransactionUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val fakeResponse = mockk<List<DailyTransactionResponse>>()
    private val homeRepositoryMock = mockk<HomeRepository>()
    private val dailyTransactionMapperMock = mockk<DailyTransactionMapper>()
    private val sut = DailyTransactionUseCase(homeRepositoryMock, dailyTransactionMapperMock)

    @Test
    fun `given repository when fetch data with success then call mapper`() = runTest {
        val fakeResult = Result.success(fakeResponse)

        coEvery {
            homeRepositoryMock.getTransactions()
        } returns fakeResult

        sut()

        verify(exactly = 1) {
            dailyTransactionMapperMock.mapper(fakeResponse)
        }
    }

    @Test
    fun `given repository when fetch data with exception then do not call mapper`() = runTest {
        coEvery {
            homeRepositoryMock.getTransactions()
        } throws StonksApiException("ops", mockk())

        sut()

        verify(exactly = 0) {
            dailyTransactionMapperMock.mapper(fakeResponse)
        }
    }
}
