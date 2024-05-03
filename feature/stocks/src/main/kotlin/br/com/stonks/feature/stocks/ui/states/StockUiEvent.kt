package br.com.stonks.feature.stocks.ui.states

import br.com.stonks.common.states.UiEvent

internal sealed class StockUiEvent : UiEvent {

    data object RegisterAlert : StockUiEvent()
}
