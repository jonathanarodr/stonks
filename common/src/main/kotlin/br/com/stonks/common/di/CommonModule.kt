package br.com.stonks.common.di

import android.content.res.AssetManager
import com.google.gson.Gson
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val commonModule = module {

    factory {
        Gson()
    }

    factory<AssetManager> {
        androidApplication().assets
    }
}
