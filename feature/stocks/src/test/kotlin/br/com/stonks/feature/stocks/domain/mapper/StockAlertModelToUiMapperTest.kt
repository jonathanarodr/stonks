package br.com.stonks.feature.stocks.domain.mapper

import br.com.stonks.designsystem.tokens.ColorToken
import br.com.stonks.feature.stocks.domain.model.StockAlertModel
import br.com.stonks.feature.stocks.domain.types.StockAlertType
import br.com.stonks.feature.stocks.domain.types.StockStatusType
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.ui.model.StockAlertUiModel
import org.junit.Assert.assertEquals
import org.junit.Test

class StockAlertModelToUiMapperTest {

    private val sut = StockAlertModelToUiMapper()

    @Test
    fun `given model when call mapper then convert to ui model`() {
        val expected = createResultData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = listOf(
        StockAlertModel(
            id = 1L,
            ticket = "lorem",
            alertValue = 1.5,
            status = StockStatusType.AVAILABLE,
            notificationTrigger = StockAlertType.HIGH_PRICE,
        )
    )

    private fun createResultData() = StockAlertUiModel(
        totalAssets = 1.5,
        stockAlerts = listOf(
            AlertUiModel(
                id = 1L,
                ticket = "lorem",
                alertValue = 1.5,
                alertColor = ColorToken.HighlightGreen,
                alertIcon = br.com.stonks.designsystem.R.drawable.ic_arrow_up,
                status = StockStatusType.AVAILABLE,
                alert = StockAlertType.HIGH_PRICE,
                tagColor = ColorToken.HighlightGreen,
            )
        ),
    )
}
