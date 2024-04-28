package br.com.stonks.startup

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import br.com.stonks.feature.home.di.homeModule
import br.com.stonks.infrastructure.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

@Suppress("unused")
internal class KoinInitializer : Initializer<KoinApplication> {

    @SuppressLint("LogNotTimber")
    override fun create(context: Context): KoinApplication {
        Log.i("Startup", "Initializing koin component...")

        return startKoin {
            androidContext(context)
            allowOverride(false)
            modules(
                networkModule,
                homeModule,
            )
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(LoggerInitializer::class.java)
    }
}
