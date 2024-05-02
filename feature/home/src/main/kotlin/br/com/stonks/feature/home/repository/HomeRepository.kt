package br.com.stonks.feature.home.repository

import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse

internal interface HomeRepository {

    suspend fun getWallet(): Result<WalletResponse>

    suspend fun getTransactions(): Result<List<DailyTransactionResponse>>
}
