package br.com.stonks.common.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

private const val SubscribedTimeout: Long = 5_000

fun <T> Flow<T>.stateWith(scope: CoroutineScope, initialValue: T): StateFlow<T> {
    return this.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(SubscribedTimeout),
        initialValue = initialValue,
    )
}

fun <T> Result<T>.asFlow(dispatcher: CoroutineDispatcher = Dispatchers.IO): Flow<T> {
    return flow {
        emit(getOrThrow())
    }.flowOn(dispatcher)
}
