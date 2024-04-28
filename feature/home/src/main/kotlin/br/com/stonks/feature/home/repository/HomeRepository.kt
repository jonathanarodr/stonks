package br.com.stonks.feature.home.repository

internal interface HomeRepository {

    suspend fun getWallet()

    suspend fun getTransactions()
}
