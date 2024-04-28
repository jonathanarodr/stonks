package br.com.stonks.infrastructure.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class MockResponseInterceptor(

): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
