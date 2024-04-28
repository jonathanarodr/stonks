package br.com.stonks.feature.home.repository

import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource

internal class HomeRepositoryImpl(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override suspend fun getWallet() {
        homeRemoteDataSource.getWallet()
    }

    override suspend fun getTransactions() {
        homeRemoteDataSource.getTransactions()
    }
}
