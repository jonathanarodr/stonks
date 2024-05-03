package br.com.stonks.feature.stocks.repository.remote.response

import com.google.gson.annotations.SerializedName

internal data class StockAlertResponse(
    @SerializedName("stock_ticket")
    val stockTicket: String,
    @SerializedName("alert_value")
    val alertValue: Double,
    @SerializedName("status")
    val status: String,
    @SerializedName("notification_trigger")
    val notificationTrigger: String,
)
