package br.com.stonks.common.di

import com.google.gson.Gson
import org.koin.dsl.module

val commonModule = module {

    factory {
        Gson()
    }
}
