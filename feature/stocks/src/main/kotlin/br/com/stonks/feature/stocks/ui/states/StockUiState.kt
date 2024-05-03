package br.com.stonks.feature.stocks.ui.states

import br.com.stonks.common.states.UiState
import br.com.stonks.feature.stocks.ui.model.StockAlertUiModel

internal sealed class StockUiState : UiState {

    data object Loading : StockUiState()

    data class Success(val data: StockAlertUiModel) : StockUiState()

    data class Error(val exception: Throwable) : StockUiState()
}
