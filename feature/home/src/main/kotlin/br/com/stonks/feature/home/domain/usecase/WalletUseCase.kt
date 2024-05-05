package br.com.stonks.feature.home.domain.usecase

import br.com.stonks.common.utils.asFlow
import br.com.stonks.feature.home.domain.mapper.WalletResponseToModelMapper
import br.com.stonks.feature.home.domain.model.WalletModel
import br.com.stonks.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

internal class WalletUseCase(
    private val homeRepository: HomeRepository,
    private val walletModelMapper: WalletResponseToModelMapper,
) {

    suspend operator fun invoke(): Flow<WalletModel> {
        return homeRepository.getWallet().mapCatching {
            walletModelMapper.mapper(it)
        }.asFlow()
    }
}
