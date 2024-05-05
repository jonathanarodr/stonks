package br.com.stonks.common.di

import android.app.Application
import android.content.Context
import io.mockk.mockk
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class CommonModuleTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `given common di module then check dependency injections`() {
        koinApplication {
            androidContext(mockk(relaxed = true))
            modules(commonModule)
            checkModules {
                withInstance<Application>()
                withInstance<Context>()
            }
        }
    }
}
