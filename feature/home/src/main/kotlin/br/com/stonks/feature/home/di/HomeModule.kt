package br.com.stonks.feature.home.di

import br.com.stonks.feature.home.domain.mapper.DailyTransactionMapper
import br.com.stonks.feature.home.domain.mapper.WalletMapper
import br.com.stonks.feature.home.domain.usecase.DailyTransactionUseCase
import br.com.stonks.feature.home.domain.usecase.WalletUseCase
import br.com.stonks.feature.home.repository.HomeRepository
import br.com.stonks.feature.home.repository.HomeRepositoryImpl
import br.com.stonks.feature.home.repository.remote.HomeApiService
import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource
import br.com.stonks.feature.home.ui.viewmodel.HomeViewModel
import br.com.stonks.infrastructure.network.provider.NetworkServiceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<HomeRepository> {
        HomeRepositoryImpl(
            homeRemoteDataSource = get(),
        )
    }

    factory {
        WalletMapper()
    }

    factory {
        DailyTransactionMapper()
    }

    factory {
        WalletUseCase(
            homeRepository = get(),
            walletMapper = get(),
        )
    }

    factory {
        DailyTransactionUseCase(
            homeRepository = get(),
            dailyTransactionMapper = get(),
        )
    }

    viewModel {
        HomeViewModel(
            walletUseCase = get(),
            dailyTransactionUseCase = get(),
        )
    }
}
