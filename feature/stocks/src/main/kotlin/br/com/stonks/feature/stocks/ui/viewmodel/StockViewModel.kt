package br.com.stonks.feature.stocks.ui.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.stonks.common.states.ViewModelState
import br.com.stonks.feature.stocks.domain.mapper.AlertUiToModelMapper
import br.com.stonks.feature.stocks.domain.mapper.StockAlertModelToUiMapper
import br.com.stonks.feature.stocks.domain.usecase.StockAlertUseCase
import br.com.stonks.feature.stocks.ui.model.AlertUiModel
import br.com.stonks.feature.stocks.ui.states.StockUiEvent
import br.com.stonks.feature.stocks.ui.states.StockUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

internal val STOCK_VM_QUALIFIER: String = StockViewModel::class.java.simpleName

internal class StockViewModel(
    private val stockAlertUseCase: StockAlertUseCase,
    private val stockAlertUiMapper: StockAlertModelToUiMapper,
    private val alertModelMapper: AlertUiToModelMapper,
) : ViewModelState<StockUiState, StockUiEvent>() {

    init {
        fetchStockAlertsContent()
    }

    override val uiState: MutableStateFlow<StockUiState> = MutableStateFlow(StockUiState.Loading)

    override fun dispatchUiEvent(uiEvent: StockUiEvent) {
        when (uiEvent) {
            is StockUiEvent.SaveAlert -> saveAlert(uiEvent.data)
            is StockUiEvent.RemoveAlert -> removeAlert(uiEvent.id)
        }
    }

    private suspend fun listStockAlerts() {
        stockAlertUseCase.listStockAlerts().catch {
            Timber.e(it, "Failure to fetch the stock alert content")
            uiState.value = StockUiState.Error(it)
        }.map {
            stockAlertUiMapper.mapper(it)
        }.collectLatest {
            uiState.value = StockUiState.Success(it)
        }
    }

    private fun fetchStockAlertsContent() {
        viewModelScope.launch {
            listStockAlerts()
        }
    }

    private fun saveAlert(alert: AlertUiModel) {
        viewModelScope.launch {
            stockAlertUseCase.saveStockAlert(alertModelMapper.mapper(alert))
            listStockAlerts()
        }
    }

    private fun removeAlert(alertId: Long) {
        viewModelScope.launch {
            stockAlertUseCase.deleteStockAlert(alertId)
            listStockAlerts()
        }
    }
}
