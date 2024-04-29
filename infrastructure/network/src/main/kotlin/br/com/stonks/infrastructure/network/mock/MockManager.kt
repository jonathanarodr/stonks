package br.com.stonks.infrastructure.network.mock

import android.content.res.AssetManager
import br.com.stonks.common.android.openFile
import br.com.stonks.infrastructure.network.types.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

internal class MockManager(
    private val assetManager: AssetManager,
) {

    private companion object {
        const val ASSET_EXTENSION = ".json"
    }

    operator fun invoke(endpoint: String): MockResponse {
        val assetFileName = configureAssetFileName(endpoint)
        val assetContent = readAssetContent(assetFileName)

        return createMockResponse(
            endpoint = endpoint,
            payload = assetContent,
        )
    }

    private fun configureAssetFileName(endpoint: String): String {
        val fileName = if (endpoint.startsWith("/")) {
            endpoint.substring(1)
        } else {
            endpoint
        }

        return fileName + ASSET_EXTENSION
    }

    private fun readAssetContent(fileName: String): String? {
        return assetManager.openFile(fileName)
    }

    private fun createMockResponse(endpoint: String, payload: String?): MockResponse {
        return if (payload.isNullOrEmpty()) {
            MockResponse(
                code = 500,
                message = "Internal Server Error",
                body = createResponseBody(
                    content = "Something went wrong on the mock server with endpoint $endpoint",
                    mediaType = MediaType.TEXT_PLAIN,
                )
            )
        } else {
            MockResponse(
                code = 200,
                message = "OK",
                body = createResponseBody(
                    content = payload,
                    mediaType = MediaType.APPLICATION_JSON,
                )
            )
        }
    }

    private fun createResponseBody(content: String, mediaType: MediaType): ResponseBody {
        return content.toResponseBody(mediaType.type.toMediaType())
    }
}
