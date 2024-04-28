package br.com.stonks.infrastructure.network.interceptor

import br.com.stonks.infrastructure.network.mock.MockManager
import br.com.stonks.infrastructure.network.mock.MockResponse
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response

internal class MockResponseInterceptor(
    private val mockManager: MockManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return mockManager(
            endpoint = chain.request().url.encodedPath
        ).run {
            createMockedResponse(this)
        }
    }

    private fun createMockedResponse(response: MockResponse): Response {
        return Response.Builder()
            .code(response.code)
            .message(response.message)
            .body(response.body)
            .protocol(Protocol.HTTP_1_1)
            .build()
    }
}
