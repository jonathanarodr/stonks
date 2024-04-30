package br.com.stonks.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> Result<T>.asFlow(dispatcher: CoroutineDispatcher = Dispatchers.Default): Flow<T> {
    return flow {
        emit(getOrThrow())
    }.flowOn(dispatcher)
}
