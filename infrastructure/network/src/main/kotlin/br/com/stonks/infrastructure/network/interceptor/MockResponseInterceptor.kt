package br.com.stonks.infrastructure.network.interceptor

import br.com.stonks.infrastructure.network.mock.MockManager
import br.com.stonks.infrastructure.network.mock.MockPrettyLogger
import br.com.stonks.infrastructure.network.mock.MockResponse
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response

internal class MockResponseInterceptor(
    private val mockManager: MockManager,
    private val prettyLogger: MockPrettyLogger,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return mockManager(
            endpoint = chain.request().url.encodedPath
        ).run {
            prettyLogger(this)
            createMockedResponse(chain.request(), this)
        }
    }

    private fun createMockedResponse(
        request: Request,
        response: MockResponse,
    ): Response {
        return Response.Builder()
            .code(response.code)
            .message(response.message)
            .body(response.body)
            .protocol(Protocol.HTTP_1_1)
            .request(request)
            .build()
    }
}
