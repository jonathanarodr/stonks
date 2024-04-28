package br.com.stonks.infrastructure.network.mock

import okhttp3.ResponseBody

internal data class MockResponse(
    val code: Int,
    val message: String,
    val body: ResponseBody,
)
