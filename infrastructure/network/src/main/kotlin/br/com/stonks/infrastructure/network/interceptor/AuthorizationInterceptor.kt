package br.com.stonks.infrastructure.network.interceptor

import br.com.stonks.infrastructure.network.BuildConfig
import br.com.stonks.infrastructure.network.types.HeaderType
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(HeaderType.AUTHORIZATION.key, createAuthorizationHeader())
            .build()

        return chain.proceed(request)
    }

    private fun createAuthorizationHeader() = "token ${BuildConfig.AUTHORIZATION_KEY}"
}
