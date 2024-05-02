package br.com.stonks.feature.home.ui.states

import br.com.stonks.common.states.UiState
import br.com.stonks.feature.home.ui.model.HomeUiModel

internal sealed class HomeUiState : UiState {

    data object Loading : HomeUiState()

    data class Success(val data: HomeUiModel) : HomeUiState()

    data class Error(val exception: Throwable) : HomeUiState()
}
