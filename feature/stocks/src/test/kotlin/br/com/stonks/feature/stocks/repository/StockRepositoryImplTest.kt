package br.com.stonks.feature.stocks.repository

import br.com.stonks.feature.stocks.domain.mapper.StockAlertEntityToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToEntityMapper
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity
import br.com.stonks.feature.stocks.repository.local.StockLocalDataSource
import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class StockRepositoryImplTest {

    private val fakeEntity = mockk<StockAlertEntity>()
    private val fakeResponse = mockk<StockAlertResponse>()
    private val stockRemoteDataSourceMock = mockk<StockRemoteDataSource>(relaxed = true)
    private val stockLocalDataSourceMock = mockk<StockLocalDataSource>(relaxed = true)
    private val stockAlertResponseMapperMock = mockk<StockAlertEntityToResponseMapper>(relaxed = true)
    private val stockAlertEntityMapperMock = mockk<StockAlertResponseToEntityMapper>(relaxed = true)

    private val sut = StockRepositoryImpl(
        stockRemoteDataSource = stockRemoteDataSourceMock,
        stockLocalDataSource = stockLocalDataSourceMock,
        stockAlertResponseMapper = stockAlertResponseMapperMock,
        stockAlertEntityMapper = stockAlertEntityMapperMock,
    )

    @Test
    fun `given repository when get remote alerts then call remote data source`() = runTest {
        sut.getRemoteStockAlerts()

        coVerify { stockRemoteDataSourceMock.getStockAlerts() }
    }

    @Test
    fun `given repository when get local alerts then call local data source`() = runTest {
        coEvery {
            stockLocalDataSourceMock.listStockAlerts()
        } returns Result.success(listOf(fakeEntity))

        sut.listStockAlerts()

        coVerifyOrder {
            stockLocalDataSourceMock.listStockAlerts()
            stockAlertResponseMapperMock.mapper(fakeEntity)
        }
    }

    @Test
    fun `given repository when get alert then call local data source`() = runTest {
        val alertId = 1L

        coEvery {
            stockLocalDataSourceMock.getStockAlert(alertId)
        } returns Result.success(fakeEntity)

        sut.getStockAlert(alertId)

        coVerifyOrder {
            stockLocalDataSourceMock.getStockAlert(alertId)
            stockAlertResponseMapperMock.mapper(fakeEntity)
        }
    }

    @Test
    fun `given repository when insert alert then call local data source`() = runTest {
        coEvery {
            stockAlertEntityMapperMock.mapper(fakeResponse)
        } returns fakeEntity

        coEvery {
            stockLocalDataSourceMock.insertStockAlert(fakeEntity)
        } returns mockk()

        sut.insertStockAlert(fakeResponse)

        coVerify {
            stockLocalDataSourceMock.insertStockAlert(fakeEntity)
        }
    }

    @Test
    fun `given repository when update alert then call local data source`() = runTest {
        coEvery {
            stockAlertEntityMapperMock.mapper(fakeResponse)
        } returns fakeEntity

        coEvery {
            stockLocalDataSourceMock.updateStockAlert(fakeEntity)
        } returns mockk()

        sut.updateStockAlert(fakeResponse)

        coVerify {
            stockLocalDataSourceMock.updateStockAlert(fakeEntity)
        }
    }

    @Test
    fun `given repository when delete alert then call local data source`() = runTest {
        val alertId = 1L

        coEvery {
            stockLocalDataSourceMock.deleteStockAlert(alertId)
        } returns mockk()

        sut.deleteStockAlert(alertId)

        coVerify {
            stockLocalDataSourceMock.deleteStockAlert(alertId)
        }
    }
}
