package br.com.stonks.infrastructure.network.types

internal enum class MediaType(val type: String) {
    APPLICATION_JSON("application/json; charset=utf-8"),
    TEXT_PLAIN("text/plain; charset=utf-8");
}
