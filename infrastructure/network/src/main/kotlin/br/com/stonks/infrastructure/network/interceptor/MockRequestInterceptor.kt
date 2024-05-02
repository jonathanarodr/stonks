package br.com.stonks.infrastructure.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class MockRequestInterceptor : Interceptor {

    private companion object {
        const val ASSET_EXTENSION = ".json"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val mockUrl = chain.request().url.toString() + ASSET_EXTENSION
        val request = chain.request()
            .newBuilder()
            .url(mockUrl)
            .build()

        return chain.proceed(request)
    }
}
