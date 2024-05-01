package br.com.stonks.infrastructure.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class MockRequestInterceptor : Interceptor {

    private companion object {
        const val ASSET_EXTENSION = ".json"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request().url
            .newBuilder()
            .addPathSegment(ASSET_EXTENSION)
            .build()

        val request = chain.request()
            .newBuilder()
            .url(httpUrl)
            .build()

        return chain.proceed(request)
    }
}
