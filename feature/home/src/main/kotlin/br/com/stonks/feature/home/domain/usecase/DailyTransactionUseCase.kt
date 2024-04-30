package br.com.stonks.feature.home.domain.usecase

import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.home.domain.mapper.DailyTransactionMapper
import br.com.stonks.feature.home.domain.model.DailyTransactionModel
import br.com.stonks.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class DailyTransactionUseCase(
    private val homeRepository: HomeRepository,
    private val dailyTransactionMapper: DailyTransactionMapper,
) {

    suspend operator fun invoke(): Flow<DailyTransactionModel> {
        return homeRepository.getTransactions().mapCatching {
            dailyTransactionMapper.mapper(it)
        }.asFlow()
    }
}
