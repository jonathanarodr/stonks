package br.com.stonks.infrastructure.network.di

import br.com.stonks.infrastructure.network.builder.OkHttpBuilder
import br.com.stonks.infrastructure.network.builder.RetrofitBuilder
import br.com.stonks.infrastructure.network.interceptor.AuthorizationInterceptor
import br.com.stonks.infrastructure.network.interceptor.ContentTypeInterceptor
import br.com.stonks.infrastructure.network.interceptor.MockResponseInterceptor
import br.com.stonks.infrastructure.network.mock.MockManager
import br.com.stonks.infrastructure.network.mock.MockPrettyLogger
import br.com.stonks.infrastructure.network.provider.NetworkServiceProvider
import br.com.stonks.infrastructure.network.provider.NetworkServiceProviderImpl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    factory {
        MockPrettyLogger()
    }

    factory {
        MockManager(
            assetManager = get(),
        )
    }

    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    } bind Interceptor::class

    factory {
        MockResponseInterceptor(
            mockManager = get(),
            prettyLogger = get(),
        )
    } bind Interceptor::class

    factory {
        AuthorizationInterceptor()
    } bind Interceptor::class

    factory {
        ContentTypeInterceptor()
    } bind Interceptor::class

    factory {
        OkHttpBuilder(
            interceptors = getAll(),
        )
    }

    factory {
        RetrofitBuilder(
            okHttpBuilder = get(),
        )
    }

    single<NetworkServiceProvider> {
        NetworkServiceProviderImpl(
            retrofitBuilder = get()
        )
    }
}
