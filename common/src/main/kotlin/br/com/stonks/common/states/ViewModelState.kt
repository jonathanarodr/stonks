package br.com.stonks.common.states

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

interface StateHolder<State : UiState, Event : UiEvent> {
    val uiState: MutableStateFlow<State>
    fun dispatchUiEvent(uiEvent: Event)
}

abstract class ViewModelState<State : UiState, Event : UiEvent> : ViewModel(), StateHolder<State, Event>
