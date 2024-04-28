package br.com.stonks.feature.home.di

import br.com.stonks.feature.home.repository.HomeRepository
import br.com.stonks.feature.home.repository.HomeRepositoryImpl
import br.com.stonks.feature.home.repository.remote.HomeApiService
import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource
import br.com.stonks.infrastructure.network.provider.NetworkServiceProvider
import org.koin.dsl.module

val homeModule = module {

    single {
        get<NetworkServiceProvider>().providerService(
            service = HomeApiService::class.java,
        )
    }

    factory {
        HomeRemoteDataSource(
            homeApi = get(),
        )
    }

    factory {
        HomeRemoteDataSource(
            homeApi = get(),
        )
    }

    factory<HomeRepository> {
        HomeRepositoryImpl(
            homeRemoteDataSource = get(),
        )
    }
}
