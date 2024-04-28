package br.com.stonks.feature.home.repository.remote

internal class HomeRemoteDataSource(
    private val homeApi: HomeApiService,
) {

    suspend fun getWallet(): String {
        return homeApi.getWallet()
    }

    suspend fun getTransactions(): String {
        return homeApi.getTransactions()
    }
}
