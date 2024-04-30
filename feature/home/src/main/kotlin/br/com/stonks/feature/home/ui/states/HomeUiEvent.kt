package br.com.stonks.feature.home.ui.states

import br.com.stonks.common.states.UiEvent

internal sealed class HomeUiEvent : UiEvent {

    data object StartHome : HomeUiEvent()
}
