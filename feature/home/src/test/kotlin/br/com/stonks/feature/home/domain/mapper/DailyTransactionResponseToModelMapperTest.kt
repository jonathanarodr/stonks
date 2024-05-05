package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.domain.model.TransactionModel
import br.com.stonks.feature.home.domain.types.TransactionType
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.TransactionResponse
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class DailyTransactionResponseToModelMapperTest {

    private val transactionDate = Date()
    private val sut = DailyTransactionResponseToModelMapper()

    @Test
    fun `given response data when call mapper then convert to model`() {
        val expected = createExpectedData()
        val result = sut.mapper(createInputData())

        assertEquals(expected, result)
    }

    private fun createInputData() = listOf(
        DailyTransactionResponse(
            date = transactionDate,
            dailyBalance = 1.0,
            transactions = listOf(
                TransactionResponse(
                    type = "income",
                    description = "lorem",
                    value = 1.0,
                )
            )
        )
    )

    private fun createExpectedData() = listOf(
        DailyTransactionModel(
            date = transactionDate,
            dailyBalance = 1.0,
            transactions = listOf(
                TransactionModel(
                    type = TransactionType.INCOME,
                    description = "lorem",
                    value = 1.0,
                )
            )
        )
    )
}
