package br.com.stonks.common.di

import br.com.stonks.common.android.JsonAssetManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val commonModule = module {

    factory {
        Gson()
    }

    factory {
        JsonAssetManager(
            assets = androidApplication().assets
        )
    }
}
