package br.com.stonks.feature.home.repository

import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse

internal class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override suspend fun getWallet(): Result<WalletResponse> {
        return homeRemoteDataSource.getWallet()
    }

    override suspend fun getTransactions(): Result<List<DailyTransactionResponse>> {
        return homeRemoteDataSource.getTransactions()
    }
}
