package br.com.stonks.infrastructure.network.provider

import br.com.stonks.infrastructure.network.builder.RetrofitBuilder
import retrofit2.Retrofit

interface NetworkServiceProvider {

    fun <T> providerService(service: Class<T>): T
}

internal class NetworkServiceProviderImpl(
    private val retrofitBuilder: RetrofitBuilder
) : NetworkServiceProvider {

    private val retrofit: Retrofit by lazy {
        retrofitBuilder.build()
    }

    override fun <T> providerService(service: Class<T>): T {
        return retrofit.create(service)
    }
}
