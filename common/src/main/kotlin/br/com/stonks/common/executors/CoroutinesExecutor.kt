package br.com.stonks.common.executors

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

open class CoroutinesExecutor(
    private val dispatcher: CoroutineDispatcher,
) {

    protected open suspend fun <T> execute(
        task: suspend () -> T,
    ): Result<T> {
        return runCatching {
            withContext(dispatcher) {
                task()
            }
        }
    }
}
