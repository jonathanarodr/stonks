package br.com.stonks.common.executors

import br.com.stonks.common.exception.ResultError
import br.com.stonks.common.exception.StonksIOException
import kotlinx.coroutines.Dispatchers

open class StorageExecutor : CoroutinesExecutor(Dispatchers.IO) {

    public override suspend fun <T> execute(task: suspend () -> T): Result<T> {
        return super.execute(task).fold(
            onSuccess = ::onSuccess,
            onFailure = ::onFailure
        )
    }

    private fun <T> onSuccess(value: T): Result<T> {
        return Result.success(value)
    }

    private fun <T> onFailure(exception: Throwable): Result<T> {
        return Result.failure(
            StonksIOException(
                errorType = ResultError.IO,
                message = "Unable to execute read or write on storage",
                cause = exception,
            )
        )
    }
}
