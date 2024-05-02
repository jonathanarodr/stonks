package br.com.stonks.common.states

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

interface StateHolder<State : UiState, Event : UiEvent> {
    val uiState: StateFlow<State>
    fun dispatchUiEvent(uiEvent: Event)
}

abstract class ViewModelState<State : UiState, Event : UiEvent> : ViewModel(), StateHolder<State, Event>
