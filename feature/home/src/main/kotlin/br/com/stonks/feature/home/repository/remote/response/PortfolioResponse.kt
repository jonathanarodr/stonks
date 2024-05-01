package br.com.stonks.feature.home.repository.remote.response

import com.google.gson.annotations.SerializedName

internal data class PortfolioResponse(
    @SerializedName("portfolio_name")
    val portfolioName: String,
    @SerializedName("portfolio_type")
    val portfolioType: String,
    @SerializedName("total_investment")
    val totalInvestment: Double,
    @SerializedName("allocation")
    val allocation: Float,
)
