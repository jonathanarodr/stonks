package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class StockAlertModelToResponseMapperTest {

    private val sut = StockAlertModelToResponseMapper()

    @Test
    fun `given model when call mapper then convert to response`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = StockAlertModel(
        id = 1L,
        ticket = "lorem",
        alertValue = 5.0,
        status = StockStatusType.AVAILABLE,
        notificationTrigger = StockAlertType.LOW_PRICE,
    )

    private fun createExpectedData() = StockAlertResponse(
        id = 1L,
        stockTicket = "lorem",
        alertValue = 5.0,
        status = "available",
        notificationTrigger = "low",
    )
}
