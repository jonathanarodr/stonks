package br.com.stonks.feature.home.domain.usecase

import br.com.stonks.feature.home.domain.model.HomeContentModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class HomeContentUseCase(
    private val walletUseCase: WalletUseCase,
    private val dailyTransactionUseCase: DailyTransactionUseCase,
) {

    suspend fun fetchData(): Flow<HomeContentModel> {
        return walletUseCase().combine(dailyTransactionUseCase()) { wallet, transactions ->
            HomeContentModel(wallet, transactions)
        }
    }
}
