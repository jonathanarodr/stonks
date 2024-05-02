package br.com.stonks.feature.home.repository.remote

import br.com.stonks.feature.home.repository.remote.response.DailyTransactionResponse
import br.com.stonks.feature.home.repository.remote.response.WalletResponse
import br.com.stonks.infrastructure.network.BuildConfig
import retrofit2.http.GET

internal interface HomeApiService {

    @GET("${BuildConfig.SERVER_PATH}/wallet")
    suspend fun getWallet(): WalletResponse

    @GET("${BuildConfig.SERVER_PATH}/transactions")
    suspend fun getTransactions(): List<DailyTransactionResponse>
}
