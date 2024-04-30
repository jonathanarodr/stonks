package br.com.stonks.feature.home.repository.remote

import br.com.stonks.common.executors.ApiExecutor
import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse

internal class HomeRemoteDataSource(
    private val homeApi: HomeApiService,
) : ApiExecutor() {

    suspend fun getWallet(): Result<WalletResponse> = execute {
        homeApi.getWallet()
    }

    suspend fun getTransactions(): Result<DailyTransactionResponse> = execute {
        homeApi.getTransactions()
    }
}
