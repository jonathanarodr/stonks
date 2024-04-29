package br.com.stonks.infrastructure.network.mock

import timber.log.Timber
import java.io.IOException

internal class MockPrettyLogger {

    operator fun invoke(mockResponse: MockResponse) {
        try {
            Timber.d(mockResponse.prettyLog())
        } catch (_: IOException) {
            return
        }
    }

    private fun MockResponse.prettyLog(): String = """
        .:: MOCK PRETTY LOGGER ::.
        --> START REQUEST
            Asset file: $fileName
        --> END REQUEST
        <-- START RESPONSE
            Status: $code $message
            Content-Type: ${body.contentType()}
            Content-Length: ${body.contentLength()} 
        <-- END RESPONSE
    """.trimIndent()
}
