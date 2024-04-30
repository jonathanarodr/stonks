package br.com.stonks.feature.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.stonks.common.exception.StonksApiException
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.home.domain.usecase.DailyTransactionUseCase
import br.com.stonks.feature.home.domain.usecase.WalletUseCase
import br.com.stonks.feature.home.ui.states.HomeUiEvent
import br.com.stonks.feature.home.ui.states.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val walletUseCase: WalletUseCase,
    private val dailyTransactionUseCase: DailyTransactionUseCase,
) : ViewModelState<HomeUiState, HomeUiEvent>() {

    override val uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    override fun dispatchUiEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.StartHome -> fetchHomeData()
        }
    }

    private fun fetchHomeData() {
        viewModelScope.launch {
            fetchWalletData()
            fetchDailyTransactionData()
        }
    }

    private suspend fun fetchWalletData() {
        walletUseCase().catch {
            uiState.value = HomeUiState.Error(it as StonksApiException)
        }.collect {
            uiState.value = HomeUiState.WalletSuccess(it)
        }
    }

    private suspend fun fetchDailyTransactionData() {
        dailyTransactionUseCase().catch {
            uiState.value = HomeUiState.Error(it as StonksApiException)
        }.collect {
            uiState.value = HomeUiState.DailyTransactionSuccess(it)
        }
    }
}
