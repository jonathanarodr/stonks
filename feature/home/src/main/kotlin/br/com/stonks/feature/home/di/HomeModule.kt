package br.com.stonks.feature.home.di

import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.home.domain.mapper.DailyTransactionMapper
import br.com.stonks.feature.home.domain.mapper.WalletMapper
import br.com.stonks.feature.home.domain.usecase.DailyTransactionUseCase
import br.com.stonks.feature.home.domain.usecase.HomeContentUseCase
import br.com.stonks.feature.home.domain.usecase.WalletUseCase
import br.com.stonks.feature.home.repository.HomeRepository
import br.com.stonks.feature.home.repository.HomeRepositoryImpl
import br.com.stonks.feature.home.repository.remote.HomeApiService
import br.com.stonks.feature.home.repository.remote.HomeRemoteDataSource
import br.com.stonks.feature.home.ui.mapper.HomeUiMapper
import br.com.stonks.feature.home.ui.viewmodel.HOME_VM_QUALIFIER
import br.com.stonks.feature.home.ui.viewmodel.HomeViewModel
import br.com.stonks.infrastructure.network.provider.NetworkServiceProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
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
        HomeUiMapper()
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

    factory {
        HomeContentUseCase(
            walletUseCase = get(),
            dailyTransactionUseCase = get(),
        )
    }

    viewModel(qualifier = named(HOME_VM_QUALIFIER)) {
        HomeViewModel(
            homeContentUseCase = get(),
            homeUiMapper = get(),
        )
    } bind ViewModelState::class
}
