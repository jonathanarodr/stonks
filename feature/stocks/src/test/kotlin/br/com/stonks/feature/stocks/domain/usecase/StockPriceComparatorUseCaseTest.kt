package br.com.stonks.feature.stocks.domain.usecase

import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.repository.StockRepository
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class StockPriceComparatorUseCaseTest {

    private val stockRepositoryMock = mockk<StockRepository>()
    private val sut = StockPriceComparatorUseCase(stockRepositoryMock)

    @Test
    fun `given comparator when match alert with same price then return unknown type`() = runTest {
        coEvery {
            stockRepositoryMock.getRemoteStockAlerts()
        } returns Result.success(
            listOf(createFakeResponse("AAPL34", 50.0))
        )

        val result = sut.checkStockPrice(
            createFakeResponse("AAPL34", 50.0)
        )

        assertEquals(StockAlertType.UNKNOWN, result)
    }

    @Test
    fun `given comparator when match alert with high price then return low type`() = runTest {
        coEvery {
            stockRepositoryMock.getRemoteStockAlerts()
        } returns Result.success(
            listOf(createFakeResponse("AAPL34", 50.0))
        )

        val result = sut.checkStockPrice(
            createFakeResponse("AAPL34", 51.0)
        )

        assertEquals(StockAlertType.LOW_PRICE, result)
    }

    @Test
    fun `given comparator when match alert with low price then return high type`() = runTest {
        coEvery {
            stockRepositoryMock.getRemoteStockAlerts()
        } returns Result.success(
            listOf(createFakeResponse("AAPL34", 51.0))
        )

        val result = sut.checkStockPrice(
            createFakeResponse("AAPL34", 50.0)
        )

        assertEquals(StockAlertType.HIGH_PRICE, result)
    }

    @Test
    fun `given comparator when do not match alert then return unknown type`() = runTest {
        coEvery {
            stockRepositoryMock.getRemoteStockAlerts()
        } returns Result.success(
            listOf(createFakeResponse("AAPL34", 50.0))
        )

        val result = sut.checkStockPrice(
            createFakeResponse("ITSA4", 51.0)
        )

        assertEquals(StockAlertType.UNKNOWN, result)
    }

    private fun createFakeResponse(
        stockTicket: String,
        alertValue: Double,
    ) = StockAlertResponse(
        id = 1L,
        stockTicket = stockTicket,
        alertValue = alertValue,
        status = "",
        notificationTrigger = "",
    )
}
