package br.com.stonks.feature.home.repository.remote.response

import com.google.gson.annotations.SerializedName

internal data class TransactionResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("value")
    val value: Double,
)
