package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.feature.stocks.repository.local.StockAlertEntity
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class StockAlertResponseToEntityMapperTest {

    private val sut = StockAlertResponseToEntityMapper()

    @Test
    fun `given response data when call mapper then convert to entity`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = StockAlertResponse(
        id = 1L,
        stockTicket = "lorem",
        alertValue = 8.0,
        status = "available",
        notificationTrigger = "high",
    )

    private fun createExpectedData() = StockAlertEntity(
        id = 1L,
        ticket = "lorem",
        alertValue = 8.0,
        status = "available",
        notificationTrigger = "high",
    )
}
