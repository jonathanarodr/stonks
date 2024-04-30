package br.com.stonks.feature.home.domain.model

import br.com.stonks.feature.home.domain.types.TransactionType

internal data class TransactionModel(
    val type: TransactionType,
    val description: String,
    val value: Double,
)
