package br.com.stonks.infrastructure.network.builder

import br.com.stonks.infrastructure.network.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RetrofitBuilder(
    private val okHttpBuilder: OkHttpBuilder,
) {

    fun build(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }
}
