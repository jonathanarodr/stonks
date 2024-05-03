package br.com.stonks.common.executors

import br.com.stonks.testing.rules.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StorageExecutorTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val task = mockk<(suspend () -> Unit)>()
    private val executor = StorageExecutor()

    @Test
    fun `given execute task when invoke is called with success then return valid result`() {
        runTest {
            coEvery { task.invoke() } returns Unit

            val result = executor.execute {
                task()
            }

            Assert.assertTrue(result.isSuccess)
        }
    }

    @Test
    fun `given execute task when invoke is called with failure then return valid result`() {
        runTest {
            coEvery { task.invoke() } throws Exception("Task failure")

            val result = executor.execute {
                task()
            }

            Assert.assertTrue(result.isFailure)
        }
    }
}
