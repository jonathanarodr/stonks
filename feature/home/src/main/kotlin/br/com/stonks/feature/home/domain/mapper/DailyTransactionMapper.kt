package br.com.stonks.feature.home.domain.mapper

import br.com.stonks.common.mapper.Mapper
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.domain.model.TransactionModel
import br.com.stonks.feature.home.domain.types.TransactionType
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.TransactionResponse

internal class DailyTransactionMapper : Mapper<DailyTransactionResponse, DailyTransactionModel> {

    override fun mapper(input: DailyTransactionResponse) = DailyTransactionModel(
        date = input.date,
        dailyBalance = input.dailyBalance,
        transactions = input.transactions.map(::mapperTransaction),
    )

    private fun mapperTransaction(input: TransactionResponse) = TransactionModel(
        type = TransactionType.fromString(input.type),
        description = input.description,
        value = input.value,
    )
}