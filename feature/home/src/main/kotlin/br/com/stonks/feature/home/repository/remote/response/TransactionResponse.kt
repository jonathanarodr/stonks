package br.com.stonks.feature.home.repository.remote.response

import br.com.stonks.feature.home.domain.types.TransactionType
import com.google.gson.annotations.SerializedName

internal data class TransactionResponse(
    @SerializedName("type")
    val type: TransactionType,
    @SerializedName("description")
    val description: String,
    @SerializedName("value")
    val value: Double,
)
