package br.com.stonks.feature.home.domain.usecase

import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.home.domain.mapper.DailyTransactionResponseToModelMapper
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class DailyTransactionUseCase(
    private val homeRepository: HomeRepository,
    private val dailyTransactionModelMapper: DailyTransactionResponseToModelMapper,
) {

    suspend operator fun invoke(): Flow<List<DailyTransactionModel>> {
        return homeRepository.getTransactions().mapCatching {
            dailyTransactionModelMapper.mapper(it)
        }.asFlow()
    }
}
