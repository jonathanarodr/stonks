package br.com.stonks.feature.stocks.di

import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.stocks.domain.mapper.StockAlertMapper
import br.com.stonks.feature.stocks.domain.usecase.StockAlertUseCase
import br.com.stonks.feature.stocks.repository.StockRepository
import br.com.stonks.feature.stocks.repository.StockRepositoryImpl
import br.com.stonks.feature.stocks.repository.local.StockLocalDataSource
import br.com.stonks.feature.stocks.repository.local.db.StockDataBase
import br.com.stonks.feature.stocks.repository.mapper.StockAlertEntityToResponseMapper
import br.com.stonks.feature.stocks.repository.mapper.StockAlertResponseToEntityMapper
import br.com.stonks.feature.stocks.repository.remote.StockApiService
import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
import br.com.stonks.feature.stocks.ui.mapper.StockAlertUiMapper
import br.com.stonks.feature.stocks.ui.viewmodel.STOCK_VM_QUALIFIER
import br.com.stonks.feature.stocks.ui.viewmodel.StockViewModel
import br.com.stonks.infrastructure.network.provider.NetworkServiceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val stockModule = module {

    single {
        get<NetworkServiceProvider>().providerService(
            service = StockApiService::class.java,
        )
    }

    single {
        StockDataBase(androidApplication())
    }

    single {
        get<StockDataBase>().stockAlertDao()
    }

    factory {
        StockLocalDataSource(
            stockAlertDao = get(),
        )
    }

    factory {
        StockRemoteDataSource(
            stockApiService = get(),
        )
    }

    single<StockRepository> {
        StockRepositoryImpl(
            stockRemoteDataSource = get(),
            stockLocalDataSource = get(),
            stockAlertEntityToResponseMapper = get(),
            stockAlertResponseToEntityMapper = get(),
        )
    }

    factory {
        StockAlertEntityToResponseMapper()
    }

    factory {
        StockAlertResponseToEntityMapper()
    }

    factory {
        StockAlertMapper()
    }

    factory {
        StockAlertUiMapper()
    }

    factory {
        StockAlertUseCase(
            stockAlertRepository = get(),
            stockAlertMapper = get(),
        )
    }

    viewModel(qualifier = named(STOCK_VM_QUALIFIER)) {
        StockViewModel(
            stockAlertUseCase = get(),
            stockAlertUiMapper = get(),
        )
    } bind ViewModelState::class
}
