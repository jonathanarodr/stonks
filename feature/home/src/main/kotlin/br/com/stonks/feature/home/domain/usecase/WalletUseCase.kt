package br.com.stonks.feature.home.domain.usecase

import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.home.domain.mapper.WalletMapper
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class WalletUseCase(
    private val homeRepository: HomeRepository,
    private val walletMapper: WalletMapper,
) {

    suspend operator fun invoke() : Flow<WalletModel> {
        return homeRepository.getWallet().mapCatching {
            walletMapper.mapper(it)
        }.asFlow()
    }
}
