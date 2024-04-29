package br.com.stonks.feature.home.ui.viewmodel

import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.home.ui.states.HomeUiEvent
import br.com.stonks.feature.home.ui.states.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow

internal class HomeViewModel(

) : ViewModelState<HomeUiState, HomeUiEvent>() {

    override val uiState: MutableStateFlow<HomeUiState> = TODO()

    override fun dispatchUiEvent(uiEvent: HomeUiEvent) {
        TODO("Not yet implemented")
    }
}
