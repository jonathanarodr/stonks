package br.com.stonks.feature.home.repository.remote.response

import com.google.gson.annotations.SerializedName

internal data class WalletResponse(
    @SerializedName("available_balance")
    val availableBalance: Double,
    @SerializedName("invested_balance")
    val investedBalance: Double,
    @SerializedName("total_assets")
    val totalAssets: Double,
    @SerializedName("portfolio")
    val portfolio: List<PortfolioResponse>
)
