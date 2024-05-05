package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class AlertUiToModelMapperTest {

    private val sut = AlertUiToModelMapper()

    @Test
    fun `given ui data when call mapper then convert to model`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = AlertUiModel(
        id = 1L,
        ticket = "lorem",
        alertValue = 5.0,
        status = StockStatusType.AVAILABLE,
        alert = StockAlertType.LOW_PRICE,
    )

    private fun createExpectedData() = StockAlertModel(
        id = 1L,
        ticket = "lorem",
        alertValue = 5.0,
        status = StockStatusType.AVAILABLE,
        notificationTrigger = StockAlertType.LOW_PRICE,
    )
}
