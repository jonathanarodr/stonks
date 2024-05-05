package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.repository.local.StockAlertEntity
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class StockAlertEntityToResponseMapperTest {

    private val sut = StockAlertEntityToResponseMapper()

    @Test
    fun `given entity data when call mapper then convert to response`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = StockAlertEntity(
        id = 1L,
        ticket = "lorem",
        alertValue = 1.5,
        status = StockStatusType.AVAILABLE.status,
        notificationTrigger = StockAlertType.LOW_PRICE.status,
    )

    private fun createExpectedData() = StockAlertResponse(
        id = 1L,
        stockTicket = "lorem",
        alertValue = 1.5,
        status = "available",
        notificationTrigger = "low",
    )
}
