package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.common.db.DefaultPrimaryKey
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToModelMapper
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.repository.StockRepository
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StockAlertUseCaseTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val stockRepositoryMock = mockk<StockRepository>()
    private val stockPriceComparatorUseCaseMock = mockk<StockPriceComparatorUseCase>()
    private val stockAlertModelMapperMock = mockk<StockAlertResponseToModelMapper>()
    private val stockAlertResponseMapperMock = mockk<StockAlertModelToResponseMapper>()

    private val sut = StockAlertUseCase(
        stockRepository = stockRepositoryMock,
        stockPriceComparatorUseCase = stockPriceComparatorUseCaseMock,
        stockAlertModelMapper = stockAlertModelMapperMock,
        stockAlertResponseMapper = stockAlertResponseMapperMock,
    )

    @Test
    fun `given use case when call local alerts then check price comparator and mapper to model`() = runTest {
        val fakeResponse = createFakeResponse()

        coEvery {
            stockRepositoryMock.listStockAlerts()
        } returns Result.success(listOf(fakeResponse))

        coEvery {
            stockPriceComparatorUseCaseMock.checkStockPrice(fakeResponse)
        } returns StockAlertType.HIGH_PRICE

        sut.listStockAlerts()

        coVerifyOrder {
            stockPriceComparatorUseCaseMock.checkStockPrice(
                fakeResponse
            )
            stockAlertModelMapperMock.mapper(
                createFakeResponse(trigger = StockAlertType.HIGH_PRICE.status)
            )
        }
    }

    @Test
    fun `given save alert when use default primary key then call insert method`() = runTest {
        val fakeAlert = createFakeAlert()
        val fakeResponse = createFakeResponse()

        every {
            stockAlertResponseMapperMock.mapper(fakeAlert)
        } returns fakeResponse

        coEvery {
            stockRepositoryMock.insertStockAlert(fakeResponse)
        } returns mockk()

        sut.saveStockAlert(fakeAlert)

        coVerify(exactly = 1) {
            stockRepositoryMock.insertStockAlert(fakeResponse)
        }

        coVerify(exactly = 0) {
            stockRepositoryMock.updateStockAlert(any())
        }
    }

    @Test
    fun `given save alert when use generated key then call update method`() = runTest {
        val fakeAlert = createFakeAlert(id = 5L)
        val fakeResponse = createFakeResponse(id = 5L)

        every {
            stockAlertResponseMapperMock.mapper(fakeAlert)
        } returns fakeResponse

        coEvery {
            stockRepositoryMock.updateStockAlert(fakeResponse)
        } returns Result.success(mockk())

        sut.saveStockAlert(fakeAlert)

        coVerify(exactly = 1) {
            stockRepositoryMock.updateStockAlert(fakeResponse)
        }

        coVerify(exactly = 0) {
            stockRepositoryMock.insertStockAlert(any())
        }
    }

    @Test
    fun `given use case when call delete stock then call delete method`() = runTest {
        val alertId = 1L

        coEvery {
            stockRepositoryMock.deleteStockAlert(alertId)
        } returns mockk()

        sut.deleteStockAlert(alertId)

        coVerify {
            stockRepositoryMock.deleteStockAlert(alertId)
        }
    }

    private fun createFakeAlert(
        id: Long = DefaultPrimaryKey,
    ) = StockAlertModel(
        id = id,
        ticket = "lorem",
        alertValue = 1.0,
        status = StockStatusType.AVAILABLE,
        notificationTrigger = StockAlertType.HIGH_PRICE,
    )

    private fun createFakeResponse(
        id: Long = DefaultPrimaryKey,
        trigger: String = "unknown",
    ) = StockAlertResponse(
        id = id,
        stockTicket = "lorem",
        alertValue = 5.0,
        status = "available",
        notificationTrigger = trigger,
    )
}
