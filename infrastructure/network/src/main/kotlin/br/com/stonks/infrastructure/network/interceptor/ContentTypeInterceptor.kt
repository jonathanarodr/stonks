package br.com.stonks.infrastructure.network.interceptor

import br.com.stonks.infrastructure.network.types.HeaderType
import br.com.stonks.infrastructure.network.types.MediaType
import okhttp3.Interceptor
import okhttp3.Response

internal class ContentTypeInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(HeaderType.ACCEPT_CONTENT.key, MediaType.APPLICATION_GITHUB_RAW.type)
            .build()

        return chain.proceed(request)
    }
}
