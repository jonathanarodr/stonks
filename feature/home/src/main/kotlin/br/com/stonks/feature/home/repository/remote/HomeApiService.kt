package br.com.stonks.feature.home.repository.remote

import retrofit2.http.GET

internal interface HomeApiService {

    @GET("/wallet")
    suspend fun getWallet(): String

    @GET("/transactions")
    suspend fun getTransactions(): String
}
