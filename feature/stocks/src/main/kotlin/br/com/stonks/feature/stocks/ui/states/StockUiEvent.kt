package br.com.stonks.feature.stocks.ui.states

import br.com.stonks.common.states.UiEvent
import br.com.stonks.feature.stocks.ui.model.AlertUiModel

internal sealed class StockUiEvent : UiEvent {

    data class RegisterAlert(
        val data: AlertUiModel,
    ) : StockUiEvent()

    data class RemoveAlert(
        val id: Long,
    ) : StockUiEvent()
}
