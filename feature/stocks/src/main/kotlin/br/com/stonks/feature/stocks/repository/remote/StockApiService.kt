package br.com.stonks.feature.stocks.repository.remote

import br.com.stonks.feature.stocks.repository.remote.response.StockAlertResponse
import br.com.stonks.infrastructure.network.BuildConfig
import retrofit2.http.GET

internal interface StockApiService {

    @GET("${BuildConfig.SERVER_PATH}/stock-alert")
    suspend fun getStockAlerts(): List<StockAlertResponse>
}
