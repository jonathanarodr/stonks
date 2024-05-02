package br.com.stonks.feature.home.ui.states

import br.com.stonks.common.states.UiState
import br.com.stonks.feature.home.ui.model.HomeUiModel

internal sealed class HomeUiState : UiState {

    data object Loading : HomeUiState()

    data class WalletSuccess(val data: WalletModel) : HomeUiState()

    data class DailyTransactionSuccess(val data: DailyTransactionModel) : HomeUiState()

    data class Error(val exception: StonksApiException) : HomeUiState()
}
