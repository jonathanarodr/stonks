package br.com.stonks.feature.stocks.di

import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.stocks.domain.mapper.AlertUiToModelMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToModelMapper
import br.com.stonks.feature.stocks.domain.usecase.StockAlertUseCase
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToUiMapper
import br.com.stonks.feature.stocks.repository.StockRepository
import br.com.stonks.feature.stocks.repository.StockRepositoryImpl
import br.com.stonks.feature.stocks.repository.local.StockLocalDataSource
import br.com.stonks.feature.stocks.repository.local.db.StockDataBase
import br.com.stonks.feature.stocks.domain.mapper.StockAlertEntityToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToResponseMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertResponseToEntityMapper
import br.com.stonks.feature.stocks.repository.remote.StockApiService
import br.com.stonks.feature.stocks.repository.remote.StockRemoteDataSource
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

    factory {
        StockAlertEntityToResponseMapper()
    }

    factory {
        StockAlertResponseToEntityMapper()
    }

    factory {
        StockAlertResponseToModelMapper()
    }

    factory {
        StockAlertModelToUiMapper()
    }

    factory {
        StockAlertModelToResponseMapper()
    }

    factory {
        AlertUiToModelMapper()
    }

    single<StockRepository> {
        StockRepositoryImpl(
            stockRemoteDataSource = get(),
            stockLocalDataSource = get(),
            stockAlertResponseMapper = get(),
            stockAlertEntityMapper = get(),
        )
    }

    factory {
        StockAlertUseCase(
            stockAlertRepository = get(),
            stockAlertModelMapper = get(),
            stockAlertResponseMapper = get(),
        )
    }

    viewModel(qualifier = named(STOCK_VM_QUALIFIER)) {
        StockViewModel(
            stockAlertUseCase = get(),
            stockAlertUiMapper = get(),
            alertModelMapper = get(),
        )
    } bind ViewModelState::class
}
