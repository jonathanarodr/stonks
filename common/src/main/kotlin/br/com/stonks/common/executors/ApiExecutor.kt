package br.com.stonks.common.executors

import br.com.stonks.common.exception.ResultError
import br.com.stonks.common.exception.StonksApiException
import br.com.stonks.common.exception.isConnectionError
import br.com.stonks.common.exception.isRequestHttpError
import kotlinx.coroutines.Dispatchers

open class ApiExecutor : CoroutinesExecutor(Dispatchers.IO) {

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
        return when {
            exception.isConnectionError() -> {
                Result.failure(
                    StonksApiException(
                        errorType = ResultError.UNAVAILABLE_NETWORK,
                        message = "Unavailable connection to execute request",
                        cause = exception,
                    )
                )
            }
            exception.isRequestHttpError() -> {
                Result.failure(
                    StonksApiException(
                        errorType = ResultError.REQUEST_NETWORK,
                        message = "Unable to execute api call",
                        cause = exception,
                    )
                )
            }
            else -> {
                Result.failure(
                    StonksApiException(
                        errorType = ResultError.UNKNOWN,
                        message = "Unknown error when executing api call",
                        cause = exception,
                    )
                )
            }
        }
    }
}
