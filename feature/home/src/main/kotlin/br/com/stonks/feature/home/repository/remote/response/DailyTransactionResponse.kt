package br.com.stonks.feature.home.repository.remote.response

import com.google.gson.annotations.SerializedName
import java.util.Date

internal data class DailyTransactionResponse(
    @SerializedName("date")
    val date: Date,
    @SerializedName("daily_balance")
    val dailyBalance: Double,
    @SerializedName("transactions")
    val transactions: List<TransactionResponse>,
)
