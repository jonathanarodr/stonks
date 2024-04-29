package br.com.stonks.feature.home.repository.remote

import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import retrofit2.http.GET

internal interface HomeApiService {

    @GET("/wallet")
    suspend fun getWallet(): WalletResponse

    @GET("/transactions")
    suspend fun getTransactions(): DailyTransactionResponse
}
